package Staff;

import java.util.ArrayList;
import java.util.Scanner;

import Database.Database;
import Menu.Menu;

public class ManagerMainMenu {
	public static void mainMenu(Manager staff) {
		ArrayList<Menu> branchMenu = Database.readMenuList();
		Scanner sc = new Scanner(System.in);
		int staffchoice;
		do {
			System.out.println("Enter 1 to display new orders, 2 to view the details of a particular order, 3 to process order, 4 to display branch staff list, 5 to add menu item, 6 to remove menu item, 7 to update item price, 8 to update item availability, 9 to change password, 10 to logout");
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
			 else if (staffchoice == 4) {
				 staff.displayStaffList();
			 }    
			 else if (staffchoice == 5) {
				 System.out.println("Enter item name"); 
				 String itemName = sc.nextLine();
				 System.out.println("Enter price"); 
				 float price = sc.nextFloat();
				 System.out.println("Enter category"); 
				 String category = sc.nextLine();
				 staff.addItem(itemName, price, category, true, branchMenu);
			 }    
			 else if (staffchoice == 6) {
				 System.out.println("Enter name of item to edit"); 
				 String itemlabel = sc.nextLine();				 
				 staff.removeItem(itemlabel, branchMenu);
			 }   
			 else if (staffchoice == 7) {
				 System.out.println("Enter item name"); 
				 String itemName = sc.nextLine();
				 System.out.println("Enter new price"); 
				 float newPrice = sc.nextFloat();
				 staff.updatePrice(itemName, newPrice, branchMenu);
			 }
			 else if (staffchoice == 8) {
				 staff.updateAvailability();
			 }
			 else if (staffchoice == 9) {
			     System.out.println("Enter new password");
			     String pwd = sc.nextLine();
			     staff.setPassword(pwd);
			 }
			 else if (staffchoice == 10) {
			     System.out.println("Logging out");
			 }
			 else {
			     System.out.println("Invalid option");
			 }
		} while (staffchoice != 10);
		
	}
}
