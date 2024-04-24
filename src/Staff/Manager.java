package Staff;


import Menu.Menu;
import Order.Order;

import java.io.IOException;
import java.util.*;

import Exceptions.UnknownStaffRoleException;

public class Manager extends BranchStaff {

    // Constructor
    public Manager(String staffName, String loginID, String staffPassword, String role, String gender, int age, String branch) {
        super(staffName, loginID, staffPassword, role, gender, age, branch);
    }

    
	//Add, edit, or remove menu items, price, and availability. The menu items and prices might vary among branches.
	public ArrayList<Menu> addItem(String itemName, float price, String category, boolean available, ArrayList<Menu> menuList) {
        Menu newItem = new Menu(itemName,price,this.getBranch(), category, available);
        menuList.add(newItem);
        displayBranchMenu(menuList);
        return menuList;
	}
	
	public ArrayList<Menu> removeItem(String itemName, ArrayList<Menu> menuList) {
        boolean found = false;
        displayBranchMenu(menuList);
        Menu remove = null;
        for (Menu o: menuList){
            if (Objects.equals(o.getName(),itemName) & o.getBranch().equals(this.getBranch())){
                remove = o;
                System.out.println(itemName + " removed");
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("Item does not exist in this branch's menu");
        }else{
            menuList.remove(remove);
        }
        return menuList;
	}
	
	public ArrayList<Menu> updatePrice(String itemName, float newPrice, ArrayList<Menu> menuList) {
        boolean found = false;
        displayBranchMenu(menuList);
		for (Menu o: menuList){
            if (Objects.equals(o.getName(),itemName) & o.getBranch().equals(this.getBranch())){
				o.setPrice(newPrice);
				System.out.println(itemName + " price changed to $" + newPrice);
                break;
			}
		}
        if (!found){
            System.out.println("Item does not exist in this branch's menu");
        }
        return menuList;
	}

    public ArrayList<Menu> updateAvailability(String itemName, ArrayList<Menu> menuList){
        boolean found = false;
        displayBranchMenu(menuList);
        for (Menu o: menuList){
            if (Objects.equals(o.getName(),itemName) & o.getBranch().equals(this.getBranch())){
                o.setAvailability(!o.isAvailable());
                found = true;
            }
        }
        if (!found){
            System.out.println("Item does not exist in this branch's menu");
        }
        return menuList;
    }

    public void displayStaffList(ArrayList<Staff> staffList){
        System.out.println("Staff in this branch: ");
        for (Staff o: staffList){
            if (o.getBranch().equals(this.getBranch())){
                System.out.println(o.getStaffName());
            }
        }
    }

    public void displayBranchMenu(ArrayList<Menu> menuList){
        for (Menu o: menuList){
            if (o.getBranch().equals(this.getBranch()))
                System.out.println(o.getName() + o.getCategory() + o.getPrice() + o.isAvailable());
        }
    }
    
    public void staffMenu() throws IOException, UnknownStaffRoleException {
    	ManagerMainMenu.mainMenu(this);
    }
}
