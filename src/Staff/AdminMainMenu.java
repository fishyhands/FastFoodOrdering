package Staff;

import java.util.ArrayList;
import java.util.Scanner;

import Branch.Branch;
import Database.Database;
import Order.Order;

public class AdminMainMenu {
	public static void mainMenu(Admin staff) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Staff> staffList = Database.readStaffList();
		ArrayList<Branch> branchList = Database.readBranchList();
		int staffchoice;
		do {
	        System.out.println("Enter 1 to add staff account, 2 to remove staff account, 3 to edit staff account, 4 to display staff list, 5 to assign managers, 6 to promote a staff to manager, 7 to transfer staff to another branch, 8 to add payment method, 9 to remove payment method, 10 to change branch status, 11 to change password, 12 to logout");
	        staffchoice = sc.nextInt(); // non int error
	        if (staffchoice == 1) { //some possible exceptions here
	            System.out.println("Enter name"); 
	            String name = sc.nextLine();
	            System.out.println("Enter id"); 
	            String loginID = sc.nextLine();
	            System.out.println("Enter role"); 
	            String role = sc.nextLine();
	            System.out.println("Enter gender"); 
	            String gender = sc.nextLine();
	            System.out.println("Enter age"); 
	            int age = sc.nextInt();
	            System.out.println("Enter branch"); 
	            String branch = sc.nextLine();
	            staff.addStaff(name, loginID, "password", role, gender, age, branch, staffList);
	        }
	        else if (staffchoice == 2) {
	        	System.out.println("Enter name of staff to remove"); 
	            String name = sc.nextLine();
	            Staff fired = null;
	            for (Staff s : staffList) {
	            	if (s.getStaffName() == name)
	            		fired = s;
	            		break;
	            }
	            if (fired != null) staff.removeStaff(fired, staffList);
	            else System.out.println("Staff does not exist");
	        }
	        else if (staffchoice == 3) {
	        	System.out.println("Enter name of staff to edit"); 
	            String name = sc.nextLine();
	            Staff edited = null;
	            for (Staff s : staffList) {
	            	if (s.getStaffName() == name)
	            		edited = s;
	            		break;
	            }
	            if (edited != null) staff.editStaff(-1, edited, staffList);
	            else System.out.println("Staff does not exist");
	        }
	        else if (staffchoice == 4) {
	            staff.displayStaffList();
	        }    
	        else if (staffchoice == 5) {
	            staff.assignManager(branchList, staffList);
	        }    
	        else if (staffchoice == 6) {
	            staff.promoteStaff();
	        }   
	        else if (staffchoice == 7) {
	            staff.transferStaff();
	        }
	        else if (staffchoice == 8) {
	            staff.addPaymentMethod();
	        }
	        else if (staffchoice == 9) {
	            staff.removePaymentMethod();
	        }
	        else if (staffchoice == 10) {
	        	System.out.println("Enter branch name to change");
	        	String branchName = sc.nextLine();
	        	Branch target = null;
	        	for (Branch b : branchList) {
	            	if (b.getBranchName() == branchName)
	            		target = b;
	            		break;
	            }
	            if (target != null) staff.changeBranchStatus(target, branchList);
	            else System.out.println("Branch does not exist");
	        }
	        else if (staffchoice == 11) {
	            System.out.println("Enter new password");
	            String pwd = sc.nextLine();
	            staff.setPassword(pwd);
	        }
	        else if (staffchoice == 12) {
	            System.out.println("Logging out");
	        }
	        else {
	            System.out.println("Invalid option");
	        }
		} while (staffchoice != 12);
	}
}
