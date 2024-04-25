package menu;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;

public class MenuList {
	private static ArrayList<Menu> menuList;
	
	public MenuList() throws IOException {
		menuList = Database.readMenuList();
	}
	
	public static ArrayList<Menu> getMenuList() {
		return menuList;
	}
	
    public static void displayMenu(ArrayList<ArrayList<Menu>> branchMenu) {
        System.out.println("1)\tSet Meals:");
        displayItems(branchMenu.get(0));
        System.out.println("\n2)\tBurgers:");
        displayItems(branchMenu.get(1));
        System.out.println("\n3)\tSides:");
        displayItems(branchMenu.get(2));
        System.out.println("\n4)\tDrinks:");
        displayItems(branchMenu.get(3));
        System.out.println("\n5)\tOthers:");
        displayItems(branchMenu.get(4));
    }


    public static ArrayList<ArrayList<Menu>> getBranchMenu(String branch){
        ArrayList<Menu> setMeals = new ArrayList<>();
        ArrayList<Menu> burgers = new ArrayList<>();
        ArrayList<Menu> sides = new ArrayList<>();
        ArrayList<Menu> drinks = new ArrayList<>();
        ArrayList<Menu> others = new ArrayList<>();
        ArrayList<ArrayList<Menu>> branchMenu = new ArrayList<>();

        // Categorize the menu items
        for (Menu menuItem : menuList) {
            if (menuItem.getBranch().equals(branch)) {
                switch (menuItem.getCategory()) {
                    case "set meal":
                        setMeals.add(menuItem);
                        break;
                    case "burger":
                        burgers.add(menuItem);
                        break;
                    case "side":
                        sides.add(menuItem);
                        break;
                    case "drink":
                        drinks.add(menuItem);
                        break;
                    default:
                        others.add(menuItem);
                        break;
                }
            }
        }
        branchMenu.add(setMeals);
        branchMenu.add(burgers);
        branchMenu.add(sides);
        branchMenu.add(drinks);
        branchMenu.add(others);
        return branchMenu;
    }

    // Helper method to display menu items
    private static void displayItems(ArrayList<Menu> items) {
        for (Menu item : items) {
            System.out.println(item.getName() + ": $" + item.getPrice() + " [" + (item.isAvailable() ? "Available" : "Not Available") + "]");
        }
    }
    
    public static void writeMenuList() throws IOException {
    	Database.writeMenuList(menuList);
    }
}
