package staff;

import exceptions.UnknownStaffRoleException;
import order.OrderList;
import order.OrderTimer;

import java.io.IOException;
import java.util.Scanner;

public class StaffMainMenu{
    public static void mainMenu(BranchStaff staff)  throws IOException, UnknownStaffRoleException {
        Scanner sc = new Scanner(System.in);
        int staffchoice;
        do {
        	OrderTimer.timerOrder(OrderList.getOrderList()); // Remove the Orders that have expired
            System.out.println("Enter:\n\t1 to display new orders\n\t2 to view the details of a particular order\n\t3 to process order\n\t4 to change password\n\t5 to logout");
            staffchoice = sc.nextInt(); 
            if (staffchoice == 1) {
                staff.displayOrders(OrderList.getOrderList());
            }
            else if (staffchoice == 2) {
                if (OrderList.getOrderList().isEmpty()){
                    System.out.println("No new Orders");
                }
                staff.viewOrderDetails(OrderList.getOrderList());
            }
            else if (staffchoice == 3) {
                staff.processOrder(OrderList.getOrderList());
            }
            else if (staffchoice == 4) { 
            	System.out.println("Enter new password");
				 String pwd = sc.next();
				 sc.nextLine();
			     staff.setPassword(pwd);
            }
            else if (staffchoice == 5) {
                System.out.println("Logging out");
            }
            else {
                System.out.println("Invalid option");
            }
        } while (staffchoice != 5);
    }

}
