package Staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import Branch.Branch;
import Payment.Payment;
import Payment.PaymentMethod;
import Database.Database;

public class AdminMainMenu {
	public static Admin mainMenu(Admin staff) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		int staffchoice;
		ArrayList<Staff> staffList = Database.readStaffList();
		ArrayList<Branch> branchList = Database.readBranchList();
		ArrayList<PaymentMethod> paymentList = Database.readPaymentMethods();

		do {
	        System.out.println("Enter 1 to add staff account,\n 2 to remove staff account, \n3 to edit staff account, \n4 to display staff list, \n5 to assign managers, \n6 to promote a staff to manager, \n7 to transfer staff to another branch, \n8 to add payment method, \n9 to remove payment method, \n10 to change branch status, \n11 to change password, \n12 to logout");
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
	            staffList = staff.addStaff(name, loginID, "password", role, gender, age, branch, staffList);
	        }
	        else if (staffchoice == 2) {
	        	System.out.println("Enter name of staff to remove"); 
	            String name = sc.nextLine();
	            Staff fired = null;
	            for (Staff s : staffList) {
	            	if (Objects.equals(s.getStaffName(), name)) {
						fired = s;
						break;
					}
	            }
	            if (fired != null) {
					staffList = staff.removeStaff(fired, staffList);
				}
	        }
	        else if (staffchoice == 3) {
	        	System.out.println("Enter name of staff to edit"); 
	            String name = sc.nextLine();
	            Staff edited = null;
	            for (Staff s : staffList) {
	            	if (Objects.equals(s.getStaffName(), name)) {
						edited = s;
						break;
					}
	            }
	            if (edited != null) {staffList = staff.editStaff(1, edited, staffList);}
	            else {System.out.println("Staff does not exist");}
	        }
	        else if (staffchoice == 4) {
				System.out.println("Staff Name\t| Login ID\t| Branch\t| Role\t| Gender\t|");
	            for (Staff s: staffList){
					System.out.println(s.getStaffName() + "\t" + s.getLoginID() + "\t" + s.getBranch() + "\t" +s.getRole() + "\t" +s.getGender() );
				}
	        }    
	        else if (staffchoice == 5) {
	            staff.assignManager(branchList, staffList);
	        }    
	        else if (staffchoice == 6) {
				System.out.println("Enter name of staff to edit");
				String name = sc.nextLine();
				Staff edited = null;
				for (Staff s : staffList) {
					if (Objects.equals(s.getStaffName(), name)) {
						edited = s;
						break;
					}
				}
				if (edited != null) {staffList = staff.editStaff(3, edited, staffList);}
				else {System.out.println("Staff does not exist");}
			}

	        else if (staffchoice == 7) {
				System.out.println("Enter name of staff to edit");
				String name = sc.nextLine();
				Staff edited = null;
				for (Staff s : staffList) {
					if (Objects.equals(s.getStaffName(), name)) {
						edited = s;
						break;
					}
				}
				if (edited != null) {staffList= staff.editStaff(6, edited, staffList);}
				else {System.out.println("Staff does not exist");}
			}

	        else if (staffchoice == 8) {
	            paymentList = Payment.addMethod(paymentList);
	        }
	        else if (staffchoice == 9) {
	            paymentList = Payment.removeMethod(paymentList);
	        }
	        else if (staffchoice == 10) {
	        	System.out.println("Enter branch name to change");
	        	String branchName = sc.nextLine();
	        	Branch target = null;
	        	for (Branch b : branchList) {
	            	if (Objects.equals(b.getBranchName(), branchName)) {
						target = b;
						break;
					}
	            }
	            if (target != null) {branchList = staff.changeBranchStatus(target, branchList);}
	            else {System.out.println("Branch does not exist");}
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
		return staff;
	}
}
