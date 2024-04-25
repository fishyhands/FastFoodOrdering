package menu;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;

/**
 * The MenuList class manages the list of menu items.
 * It provides methods to read menu data from the database, display the menu, categorize menu items by branch and category,
 * and write menu data back to the database.
 */
public class MenuList {
    private static ArrayList<Menu> menuList;

    /**
     * Constructs a new MenuList object and initializes the menu list by reading data from the database.
     *
     * @throws IOException If an I/O error occurs while reading menu data.
     */
    public MenuList() throws IOException {
        menuList = Database.readMenuList();
    }

    /**
     * Retrieves the list of menu items.
     *
     * @return The list of menu items.
     */
    public static ArrayList<Menu> getMenuList() {
        return menuList;
    }

    /**
     * Displays the menu categorized by different categories.
     *
     * @param branchMenu The menu categorized by branch.
     */
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

    /**
     * Retrieves the menu categorized by branch.
     *
     * @param branch The branch to filter the menu items.
     * @return The menu categorized by branch.
     */
    public static ArrayList<ArrayList<Menu>> getBranchMenu(String branch) {
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

    /**
     * Writes the menu list data to the database.
     *
     * @throws IOException If an I/O error occurs while writing menu data.
     */
    public static void writeMenuList() throws IOException {
        Database.writeMenuList(menuList);
    }
    /*
    Helper method to display menu items
    *
    * @param items an arraylist of the menu items
    */

    private static void displayItems(ArrayList<Menu> items) {
        for (Menu item : items) {
            System.out.println(item.getName() + ": $" + item.getPrice() + " [" + (item.isAvailable() ? "Available" : "Not Available") + "]");
        }
    }
}
