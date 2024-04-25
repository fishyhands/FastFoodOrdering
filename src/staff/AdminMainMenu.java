package staff;

import java.io.IOException;
import java.util.Scanner;

import branch.BranchList;
import exceptions.UnknownStaffRoleException;
import order.OrderList;
import order.OrderTimer;
import payment.Payment;
import payment.PaymentMethodList;

/**
 * Represents the main menu for admin tasks accessible by an admin user.
 */
public class AdminMainMenu {

	/**
	 * Displays the main menu for admin tasks and handles user input.
	 *
	 * @param staff The admin staff member accessing the menu.
	 * @throws IOException               If an I/O error occurs.
	 * @throws ClassNotFoundException    If the class of a serialized object cannot be found.
	 * @throws UnknownStaffRoleException If an unknown staff role is encountered.
	 */
	public static void mainMenu(Admin staff) throws IOException, ClassNotFoundException, UnknownStaffRoleException {
		Scanner sc = new Scanner(System.in);
		int staffchoice;		

		do {
			OrderTimer.timerOrder(OrderList.getOrderList()); // Remove the Orders that have expired
	        System.out.println("\nManage Staff\n-------------\n1 to add staff account,\n2 to remove staff account, \n3 to edit staff name, \n4 to display staff list, \n5 to assign managers, \n6 to promote a staff to manager, \n7 to transfer staff to another branch\n\nManage Payment Methods\n-------------\n8 to add payment method, \n9 to remove payment method\n\nManage Branches\n-------------\n10 to change branch status, \n11 to open new branch\n\nAccount\n-------------\n12 to change password, \n13 to logout\n\nEnter choice");
	        staffchoice = sc.nextInt();
			sc.nextLine();// non int error
	        if (staffchoice == 1) { //some possible exceptions here
	            System.out.println("Enter name");
	            String name = sc.next();
				System.out.println("Press Enter to continue...");
				sc.nextLine();
	            System.out.println("Enter id");
	            String loginID = sc.next();
				System.out.println("Press Enter to continue...");
				sc.nextLine();
	            System.out.println("Enter role");
	            String role = sc.next();
				System.out.println("Press Enter to continue...");
				sc.nextLine();
	            System.out.println("Enter gender");
	            String gender = sc.next();
				System.out.println("Press Enter to continue...");
				sc.nextLine();
	            System.out.println("Enter age");
	            int age = sc.nextInt();
				System.out.println("Press Enter to continue...");
				sc.nextLine();
	            System.out.println("Enter branch"); 
	            String branch = sc.next();
	            staff.addStaff(name, loginID, "password", role, gender, age, branch, StaffList.getStaffList());
	        }
	        else if (staffchoice == 2) {
	        	System.out.println("Enter name of staff to remove"); 
	            String name = sc.nextLine();
	            staff.removeStaff(name, StaffList.getStaffList());
	        }
	        else if (staffchoice == 3) {
	        	System.out.println("Enter name of staff to edit"); 
	            String name = sc.nextLine(); 
	            staff.editStaff(1, name, StaffList.getStaffList());
	        }
	        else if (staffchoice == 4) {
				DisplayFilters.run(StaffList.getStaffList());
	        }    
	        else if (staffchoice == 5) {
	            staff.assignManager(BranchList.getBranchList(), StaffList.getStaffList());
	        }    
	        else if (staffchoice == 6) {
	        	System.out.println("Enter name of staff to edit"); 
	            String name = sc.nextLine(); 
	            staff.editStaff(3, name, StaffList.getStaffList());				
			}

	        else if (staffchoice == 7) {
	        	System.out.println("Enter name of staff to edit"); 
	            String name = sc.nextLine(); 
	            staff.editStaff(6, name, StaffList.getStaffList());
			}

	        else if (staffchoice == 8) {
	            Payment.addMethod(PaymentMethodList.getPaymentMethodList());
	        }
	        else if (staffchoice == 9) {
	            Payment.removeMethod(PaymentMethodList.getPaymentMethodList());
	        }
	        else if (staffchoice == 10) {
	        	System.out.println("Enter branch name to change");
	        	String branchName = sc.next();
	        	staff.changeBranchStatus(branchName, BranchList.getBranchList());
	        }
	        else if (staffchoice == 11) {
	        	System.out.println("Enter new branch name");
	        	String branchName = sc.next();
	        	System.out.println("Enter new branch location");
	        	String branchLocation = sc.next();
	        	System.out.println("Enter new branch staff quota");
	        	int staffQuota = sc.nextInt();
	        	staff.openNewBranch(BranchList.getBranchList(), branchName, branchLocation, staffQuota);
	        }
	        else if (staffchoice == 12) {
				System.out.println("Enter new password");
				String pwd = sc.next();
				sc.nextLine();
				staff.setPassword(pwd);
	        }
	        else if (staffchoice == 13) {
	            System.out.println("Logging out");
	        }
	        else {
	            System.out.println("Invalid option");
	        }
		} while (staffchoice != 13);
	}
}
