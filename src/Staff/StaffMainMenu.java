package Staff;

import Database.Database;

import Order.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffMainMenu{
	public static BranchStaff mainMenu(BranchStaff staff)  throws IOException {
		ArrayList<Order> orderList= Database.readOrderList();
		Scanner sc = new Scanner(System.in);
		int staffchoice;
		do {
			System.out.println("Enter 1 to display new orders\n2 to view the details of a particular order\n3 to process order\n4 to change password\n5 to logout");
	        staffchoice = sc.nextInt(); // non int error
	        if (staffchoice == 1) {
	            staff.displayOrders(orderList);
	        }
	        else if (staffchoice == 2) {
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
