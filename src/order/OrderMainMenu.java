package order;

import static menu.MenuBrowsing.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import branch.BranchList;
import login.Validate;
import menu.MenuBrowsing;
import payment.PaymentMainMenu;
import payment.PaymentMethodList;

/**
 * The OrderMainMenu class provides functionality for managing orders.
 * It allows users to create, check, and collect orders.
 */
public class OrderMainMenu {

    /**
     * Displays the main menu for order management.
     */
    private static void displayMainMenu(){
        System.out.println("Order Main Menu");
        System.out.println("1. Create Order");
        System.out.println("2. Check Order");
        System.out.println("3. Collect Order");
        System.out.println("4. Log out");
    }

    /**
     * Main method to run the order management functionality.
     *
     * @param args The command-line arguments.
     * @throws IOException            If an I/O error occurs.
     * @throws ClassNotFoundException If the specified class cannot be found.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            OrderTimer.timerOrder(OrderList.getOrderList()); // Remove the Orders that have expired
            displayMainMenu();
            String choiceMainMenu = scanner.next();
            switch (choiceMainMenu) {
                case "1":
                    // Get Branch
                    while (true){
                        try{
                            System.out.println("Select branch:");
                            BranchList.displayBranches();
                            System.out.println("0. Back");
                            int numBranches = BranchList.getBranchList().size();
                            int branchOption = scanner.nextInt();
                            if (branchOption > numBranches){
                                System.out.println("Enter a valid choice");
                                continue;
                            }else if(branchOption == 0){
                                break;
                            }
                            if(BranchList.getBranchList().get(branchOption-1).getOpenOrClose()){
                                String branchName = BranchList.getBranchList().get(branchOption - 1).getBranchName();
                                //call menu
                                MenuBrowsing.run(branchName);
                                //if cart empty
                                if(order.getCart().isEmpty()){
                                    System.out.println("Cart is empty. Order has not been created.");
                                    break;
                                }
                                if (!order.getCart().isEmpty()) {
                                    PaymentMainMenu.PaymentMenu(PaymentMethodList.getPaymentMethodList());
                                    OrderList.getOrderList().add(order);
                                    System.out.println("-----------------------");
                                    System.out.println("Your Order ID is: " + order.getOrderID());
                                    System.out.println("Ordered at: " + order.getTime());
                                    order.displayCart();
                                    System.out.println("-----------------------");
                                    break;
                                }
                            }else{
                                System.out.println("The branch is closed");
                                break;
                            }


                        }catch(InputMismatchException e){
                            System.out.println("Please enter a valid input");
                            scanner.nextLine();
                        }
                    }
                    break;

                case "2":
                    boolean quit = false;
                    if (OrderList.getOrderList().isEmpty()){
                        System.out.println("There are currently no orders");
                        break;
                    }
                    while (!quit){
                        // call orderList
                        System.out.println("Please enter your order ID: ");
                        int orderID = scanner.nextInt();
                        Order order = Validate.validateOrder(orderID);
                        //getting stuck in a loop here. order is always null
                        if (order != null){
                            System.out.println("Order ID:\t" + order.getOrderID() + "\n"
                                    + "Branch:\t" + order.getBranch() + "\n"
                                    + "Paid:\t" + order.isPaid() + "\n"
                                    + "Status:\t" + order.getStatus() + "\n"
                                    + "Takeaway:\t" + order.isTakeaway() + "\n");
                            order.displayCart();
                            System.out.println("Total:\t" + order.getTotalSum());
                            quit = true;
                        }
                    }
                    break;

                case "3":
                    System.out.println("Enter Order ID for collection: ");
                    int collectOrderID = scanner.nextInt();
                    CollectOrder.collectReadyOrder(collectOrderID);
                    break;

                case "4":
                    exit = true;
                    break;

                default:
                    System.out.println("Please enter a valid choice");
                    break;

            }
        }
    }
}
