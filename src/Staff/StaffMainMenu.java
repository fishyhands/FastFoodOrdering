package Staff;

import Database.Database;

import Order.Order;
import Order.OrderTimer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffMainMenu{
    public static BranchStaff mainMenu(BranchStaff staff)  throws IOException {
        ArrayList<Order> orderListUn= Database.readOrderList();
        ArrayList<Order> orderList;
        Scanner sc = new Scanner(System.in);
        int staffchoice;
        do {
            orderList = OrderTimer.timerOrder(orderListUn);
            System.out.println("Enter:\n\t1 to display new orders\n\t2 to view the details of a particular order\n\t3 to process order\n\t4 to change password\n\t5 to logout");
            staffchoice = sc.nextInt(); // non int error
            if (staffchoice == 1) {
                staff.displayOrders(orderList);
            }
            else if (staffchoice == 2) {
                if (orderList.isEmpty()){
                    System.out.println("No new Orders");
                }
                staff.viewOrderDetails(orderList);
            }
            else if (staffchoice == 3) {
                orderList = staff.processOrder(orderList);
            }
            else if (staffchoice == 4) { // maybe the input can be in the setPassword method too, then no need arguments
                System.out.println("Enter new password");
                String pwd = sc.nextLine();
                staff.setPassword(pwd);
            }
            else if (staffchoice == 5) {
                System.out.println("Logging out");
            }
            else {
                System.out.println("Invalid option");
            }
        } while (staffchoice != 5);
        Database.writeOrderList(orderList);
        return staff;
    }

}
