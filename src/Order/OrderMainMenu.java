package Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Branch.Branch;
import Branch.BranchList;
import Database.Database;
import Login.Validate;
import Menu.MenuBrowsing;
import Payment.PaymentMainMenu;
import Payment.PaymentMethod;

import static Menu.MenuBrowsing.order;

public class OrderMainMenu {
    private static void displayMainMenu(){
        System.out.println("Order Main Menu");
        System.out.println("0. Log out");
        System.out.println("1. Create Order");
        System.out.println("2. Check Order");
        System.out.println("3. Collect Order");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        ArrayList<Branch> branches = Database.readBranchList();
        ArrayList<Order> orderListUn = Database.readOrderList(); // Have not removed the Orders that have expired
        ArrayList<PaymentMethod> paymentMethods = Database.readPaymentMethods();
        ArrayList<Order> orderList = new ArrayList<>();
        while (!exit) {
            orderList = OrderTimer.timerOrder(orderListUn);
            displayMainMenu();
            String choiceMainMenu = scanner.next();
            switch (choiceMainMenu) {
                case "1":
                    // Get Branch
                    while (true){
                        try{
                            System.out.println("Select branch:");
                            BranchList.displayBranches(branches);
                            System.out.println("0. Back");
                            int numBranches = branches.size();
                            int branchOption = scanner.nextInt();
                            if (branchOption > numBranches){
                                System.out.println("Enter a valid choice");
                                continue;
                            }else if(branchOption == 0){
                                break;
                            }
                            String branchName = branches.get(branchOption - 1).getBranchName();
                            //call menu
                            MenuBrowsing.run(branchName);
                            if (!order.getCart().isEmpty()) {
                                PaymentMainMenu.PaymentMenu(paymentMethods);
                                orderList.add(order);
                                Database.writeOrderList(orderList);
                                System.out.println("-----------------------");
                                System.out.println("Your Order ID is: " + order.getOrderID());
                                System.out.println("Ordered at: " + order.getTime());
                                System.out.println("-----------------------");
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
                    if (orderList.isEmpty()){
                        System.out.println("There are currently no orders");
                        break;
                    }
                    while (!quit){
                        // call orderList
                        System.out.println("Please enter your order ID: ");
                        int orderID = scanner.nextInt();
                        Order order = Validate.validateOrder(orderList,orderID);
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
                    CollectOrder.collectReadyOrder(orderList, collectOrderID);
                    break;

                case "0":
                    exit = true;
                    break;

                default:
                    System.out.println("please enter a valid choice");
                    break;

            }
        }
        scanner.close();
        Database.writeBranchList(branches);
        Database.writeOrderList(orderList);
    }
}
