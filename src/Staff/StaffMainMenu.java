package Staff;

import java.io.IOException;
import java.util.Scanner;

public class StaffMainMenu {
	public static void mainMenu(Staff staff) {
		Scanner sc = new Scanner(System.in);
		int staffchoice;
		do {
			System.out.println("Enter 1 to display new orders, 2 to view the details of a particular order, 3 to process order, 4 to change password, 5 to logout");
	        staffchoice = sc.nextInt(); // non int error
	        if (staffchoice == 1) {
	            staff.displayOrders();
	        }
	        else if (staffchoice == 2) {
	            staff.viewOrderDetails();
	        }
	        else if (staffchoice == 3) {
	            staff.processOrder();
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
	}
}
