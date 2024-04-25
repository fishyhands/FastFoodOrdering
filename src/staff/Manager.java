package staff;


import exceptions.UnknownStaffRoleException;
import menu.Menu;
import menu.MenuList;

import java.io.IOException;
import java.util.*;

public class Manager extends BranchStaff {

    // Constructor
    public Manager(String staffName, String loginID, String staffPassword, String role, String gender, int age, String branch) {
        super(staffName, loginID, staffPassword, role, gender, age, branch);
    }

    
	//Add, edit, or remove menu items, price, and availability. The menu items and prices might vary among branches.
	public void addItem(String itemName, float price, String category, boolean available, ArrayList<Menu> menuList) {
        Menu newItem = new Menu(itemName,price,this.getBranch(), category, available);
        menuList.add(newItem);
        displayBranchMenu(menuList);
	}
	
	public boolean checkDupe(String itemName) {
		for(Menu item: MenuList.getMenuList()){
		 	if(item.getName().equalsIgnoreCase(itemName)){
				return true;
			 }
		}
		return false;
	}
	
	public void removeItem(String itemName, ArrayList<Menu> menuList) {
        boolean found = false;
        Menu remove = null;
        for (Menu o: menuList){
            if (Objects.equals(o.getName(),itemName) & o.getBranch().equals(this.getBranch())){
                remove = o;
                System.out.println(itemName + " removed");
                found = true;
                displayBranchMenu(menuList);
                break;
            }
        }
        if (!found){
            System.out.println("Item does not exist in this branch's menu");
        }else{
            menuList.remove(remove);
        }
	}
	
	public void updatePrice(String itemName, float newPrice, ArrayList<Menu> menuList) {
        boolean found = false;

		for (Menu o: menuList){
            if (Objects.equals(o.getName(),itemName) & o.getBranch().equals(this.getBranch())){
				o.setPrice(newPrice);
				System.out.println(itemName + " price changed to $" + newPrice);
                displayBranchMenu(menuList);
                found = true;
                break;
			}
		}
        if (!found){
            System.out.println("Item does not exist in this branch's menu");
        }
	}

    public void updateAvailability(String itemName, ArrayList<Menu> menuList){
        boolean found = false;

        for (Menu o: menuList){
            if (Objects.equals(o.getName(),itemName) & o.getBranch().equals(this.getBranch())){
                o.setAvailability(!o.isAvailable());
                displayBranchMenu(menuList);
                found = true;
            }
        }
        if (!found){
            System.out.println("Item does not exist in this branch's menu");
        }
    }

    public void displayStaffList(ArrayList<Staff> staffList){
        System.out.println("Staff in this branch: " + this.getBranch());
        for (Staff o: staffList){
            if (o.getBranch().equals(this.getBranch())){
                System.out.println(o.getStaffName());
            }
        }
    }

    public void displayBranchMenu(ArrayList<Menu> menuList){
        for (Menu o: menuList){
            if (o.getBranch().equals(this.getBranch()))
                System.out.println(o.getName() + " " + o.getCategory()+ " " + o.getPrice() + " " + o.isAvailable());
        }
    }
    
    public void staffMenu() throws IOException, UnknownStaffRoleException, ClassNotFoundException {
        ManagerMainMenu.mainMenu(this);
    }
}
