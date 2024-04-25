package staff;

import exceptions.UnknownStaffRoleException;
import menu.Menu;
import menu.MenuList;

import java.io.IOException;
import java.util.*;

/**
 * Represents a Manager staff member who can manage menu items, prices, and availability in a specific branch.
 * Inherits from BranchStaff.
 */
public class Manager extends BranchStaff {

    /**
     * Constructor to initialize a Manager object.
     *
     * @param staffName     The name of the manager.
     * @param loginID       The login ID of the manager.
     * @param staffPassword The password of the manager.
     * @param role          The role of the manager.
     * @param gender        The gender of the manager.
     * @param age           The age of the manager.
     * @param branch        The branch where the manager works.
     */
    public Manager(String staffName, String loginID, String staffPassword, String role, String gender, int age, String branch) {
        super(staffName, loginID, staffPassword, role, gender, age, branch);
    }

    /**
     * Adds a new menu item to the menu list.
     *
     * @param itemName  The name of the menu item to add.
     * @param price     The price of the menu item.
     * @param category  The category of the menu item.
     * @param available The availability status of the menu item.
     * @param menuList  The list of menu items to add to.
     */
    public void addItem(String itemName, float price, String category, boolean available, ArrayList<Menu> menuList) {
        Menu newItem = new Menu(itemName, price, this.getBranch(), category, available);
        menuList.add(newItem);
        displayBranchMenu(menuList);
    }

    /**
     * Checks if a menu item with the given name already exists.
     *
     * @param itemName The name of the menu item to check for duplicates.
     * @return True if a duplicate item exists, false otherwise.
     */
    public boolean checkDupe(String itemName) {
        for (Menu item : MenuList.getMenuList()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a menu item from the menu list.
     *
     * @param itemName The name of the menu item to remove.
     * @param menuList The list of menu items to remove from.
     */
    public void removeItem(String itemName, ArrayList<Menu> menuList) {
        boolean found = false;
        Menu remove = null;
        for (Menu o : menuList) {
            if (Objects.equals(o.getName(), itemName) & o.getBranch().equals(this.getBranch())) {
                remove = o;
                System.out.println(itemName + " removed");
                found = true;
                displayBranchMenu(menuList);
                break;
            }
        }
        if (!found) {
            System.out.println("Item does not exist in this branch's menu");
        } else {
            menuList.remove(remove);
        }
    }

    /**
     * Updates the price of a menu item.
     *
     * @param itemName  The name of the menu item to update the price for.
     * @param newPrice  The new price of the menu item.
     * @param menuList  The list of menu items to update.
     */
    public void updatePrice(String itemName, float newPrice, ArrayList<Menu> menuList) {
        boolean found = false;

        for (Menu o : menuList) {
            if (Objects.equals(o.getName(), itemName) & o.getBranch().equals(this.getBranch())) {
                o.setPrice(newPrice);
                System.out.println(itemName + " price changed to $" + newPrice);
                displayBranchMenu(menuList);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item does not exist in this branch's menu");
        }
    }

    /**
     * Updates the availability status of a menu item.
     *
     * @param itemName The name of the menu item to update the availability for.
     * @param menuList The list of menu items to update.
     */
    public void updateAvailability(String itemName, ArrayList<Menu> menuList) {
        boolean found = false;

        for (Menu o : menuList) {
            if (Objects.equals(o.getName(), itemName) & o.getBranch().equals(this.getBranch())) {
                o.setAvailability(!o.isAvailable());
                displayBranchMenu(menuList);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Item does not exist in this branch's menu");
        }
    }

    /**
     * Displays the list of staff members in the current branch.
     *
     * @param staffList The list of all staff members.
     */
    public void displayStaffList(ArrayList<Staff> staffList) {
        System.out.println("Staff in this branch: " + this.getBranch());
        for (Staff o : staffList) {
            if (o.getBranch().equals(this.getBranch())) {
                System.out.println(o.getStaffName());
            }
        }
    }

    /**
     * Displays the menu items available in the current branch.
     *
     * @param menuList The list of all menu items.
     */
    public void displayBranchMenu(ArrayList<Menu> menuList) {
        for (Menu o : menuList) {
            if (o.getBranch().equals(this.getBranch()))
                System.out.println(o.getName() + " " + o.getCategory() + " " + o.getPrice() + " " + o.isAvailable());
        }
    }

    /**
     * Directs the manager to the main menu for staff members.
     *
     * @throws IOException                If an I/O error occurs.
     * @throws UnknownStaffRoleException  If the staff role is unknown.
     * @throws ClassNotFoundException   If the class of a serialized object cannot be found.
     */
    public void staffMenu() throws IOException, UnknownStaffRoleException, ClassNotFoundException {
        ManagerMainMenu.mainMenu(this);
    }
}
