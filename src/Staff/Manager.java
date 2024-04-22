package Staff;

import Database.Database;
import Menu.Menu;

import java.io.IOException;
import java.util.*;

public class Manager extends Staff{

    // Constructor
    public Manager(String staffName, String loginID, String staffPassword, String role, String gender, int age, String branch) {
        super(staffName, loginID, staffPassword, role, gender, age, branch);
    }

    
	//Add, edit, or remove menu items, price, and availability. The menu items and prices might vary among branches.
	public void addItem(String itemName, float price, String category, boolean available, ArrayList<Menu> branchMenu) {
        Menu newItem = new Menu(itemName,price,this.getBranch(), category, available);
        branchMenu.add(newItem);
	}
	
	public void removeItem(String itemName, ArrayList<Menu> branchMenu) {
        for (Menu o: branchMenu){
            if (Objects.equals(o.getName(),itemName)){
                branchMenu.remove(o);
                System.out.println(itemName + " removed");
            }
        }
	}
	
	public void updatePrice(String itemName, float newPrice, ArrayList<Menu> branchMenu) {
		for (Menu o: branchMenu){
            if (Objects.equals(o.getName(),itemName)){
				o.setPrice(newPrice);
				System.out.println(itemName + " price changed to $" + newPrice);
			}
		}
	}

	//menu
    public void staffMenu() throws IOException {
    	ManagerMainMenu.mainMenu(this);
    }
}
