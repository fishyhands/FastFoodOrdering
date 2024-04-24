package Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
    

import Branch.Branch;
import Database.Database;
import Order.Order;
import Order.CreateOrder;

public class MenuBrowsing {
    public static String branchName;
    public static Order order;

    private static void displayMenuBrowsing(ArrayList<Menu> menuList, String branchName) throws IOException {
        ArrayList<ArrayList<Menu>> branchMenu = MenuList.getBranchMenu(menuList,branchName);
        System.out.println("Browsing Menu at:\t" + branchName);
        MenuList.displayMenu(branchMenu);
        order.displayCart();
    }


    public static Order run(String branchName) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        order = CreateOrder.createOrder(branchName);
        ArrayList<Menu> menuList = Database.readMenuList();
        ArrayList<Order> orderList = Database.readOrderList();
        ArrayList<ArrayList<Menu>> branchMenu = MenuList.getBranchMenu(menuList, branchName);

        while (!exit) {
            displayMenuBrowsing(menuList, branchName);
            System.out.println("Enter 0 to exit the Menu and pay");
            System.out.println("Please select which category you would like: ");
            int choiceMainMenu = scanner.nextInt();
            if (choiceMainMenu == 0) {
            	exit = true;
                float totalSum = order.calculateTotalSum();
                System.out.println("Total: " + order.getTotalSum());
            }
            else if (choiceMainMenu < 0 || choiceMainMenu > branchMenu.size()) {
            	System.out.println("Invalid choice, please try again");
            }
            else if (branchMenu.get(choiceMainMenu-1).size() <= 0) {
            	System.out.println("No available items in that category, please pick another one");
            }
            else {
            	ChoiceMenu.chooseItems(branchMenu.get(choiceMainMenu-1));
            }
        }
        return order;
    }
}