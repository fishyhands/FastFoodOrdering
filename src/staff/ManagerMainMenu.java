package staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import database.Database;
import exceptions.UnknownStaffRoleException;
import menu.Menu;
import menu.MenuList;
import order.Order;
import order.OrderList;
import order.OrderTimer;

public class ManagerMainMenu {
	public static void mainMenu(Manager staff) throws IOException, UnknownStaffRoleException {

		Scanner sc = new Scanner(System.in);
		int staffchoice;
		do {
			OrderTimer.timerOrder(OrderList.getOrderList()); // Remove the Orders that have expired
			System.out.println("Enter:\n\t1 to display new orders\n\t2 to view the details of a particular order\n\t3 to process order\n\t4 to display branch staff list\n\t5 to add menu item\n\t6 to remove menu item\n\t7 to update item price\n\t8 to update item availability\n\t9 to change password\n\t10 to logout");
			 staffchoice = sc.nextInt(); // non int error
			 sc.nextLine(); // reads enter
			 if (staffchoice == 1) {
			     staff.displayOrders(OrderList.getOrderList());
			 }
			 else if (staffchoice == 2) {
				 staff.viewOrderDetails(OrderList.getOrderList());
			 }
			 else if (staffchoice == 3) {
				 staff.processOrder(OrderList.getOrderList());
			 }
			 else if (staffchoice == 4) {
				 staff.displayStaffList(StaffList.getStaffList());
			 }    
			 else if (staffchoice == 5) {
				 System.out.println("Enter item name:");
				 String itemName = sc.nextLine();
				 //check if item already exists
				 boolean itemDupe = staff.checkDupe(itemName);				 
				 if(itemDupe) {
					 System.out.println("Item with this name already exists.");
				 }else {
					 System.out.println("Enter price:");
					 float price = sc.nextFloat();
					 System.out.println("Enter category:");
					 String category = sc.next();
					 sc.nextLine();
					 staff.addItem(itemName, price, category, true, MenuList.getMenuList());
				 }
			 }    
			 else if (staffchoice == 6) {
				 System.out.println("Enter name of item to edit:");
				 String itemlabel = sc.nextLine();				 
				 staff.removeItem(itemlabel, MenuList.getMenuList());
			 }   
			 else if (staffchoice == 7) {
				 System.out.println("Enter item name:");
				 String itemName = sc.nextLine();
				 System.out.println("Enter new price:");
				 float newPrice = sc.nextFloat();
				 staff.updatePrice(itemName, newPrice, MenuList.getMenuList());
			 }
			 else if (staffchoice == 8) {
				 System.out.println("Enter item name:");
				 String itemName = sc.nextLine();
				 staff.updateAvailability(itemName, MenuList.getMenuList());
			 }
			 else if (staffchoice == 9) {
			     System.out.println("Enter new password");
				 String pwd = sc.next();
				 sc.nextLine();
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
