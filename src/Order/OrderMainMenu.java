package Order;

import java.io.IOException;
import java.util.ArrayList;
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
        System.out.println("0. Back");
        System.out.println("1. Select Branch");
        System.out.println("2. Check Order");
        System.out.println("3. Collect Order");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        ArrayList<Branch> branches = Database.readBranchList();
        ArrayList<Order> orderList = Database.readOrderList();
        ArrayList<PaymentMethod> paymentMethods = Database.readPaymentMethods();

        while (!exit) {
            displayMainMenu();
            int choiceMainMenu = scanner.nextInt();
            switch (choiceMainMenu) {
                case 1:
                    // Get Branch
                    BranchList.displayBranches(branches);
                    int numBranches = branches.size();
                    int branchOption = scanner.nextInt();
                    if (branchOption > numBranches){
                        System.out.println("Enter a valid choice");
                        continue;
                    }
                    String branchName = branches.get(branchOption - 1).getBranchName();
                    //call menu
                    MenuBrowsing.run(branchName);
                    PaymentMainMenu.PaymentMenu(paymentMethods);                    

                case 2:
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
                case 3:
                    System.out.println("Enter Order ID for collection: ");
                    int collectOrderID = scanner.nextInt();
                    CollectOrder.collectReadyOrder(orderList, collectOrderID);
                    break;

                case 0:
                    exit = true;
                    break;

            }
        }
        scanner.close();
        Database.writeBranchList(branches);
        Database.writeOrderList(orderList);
    }
}
