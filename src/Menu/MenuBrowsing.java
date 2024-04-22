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

    static {
        try {
            order = CreateOrder.createOrder(branchName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    private static void displayMenuBrowsing(ArrayList<Menu> menuList, String branchName) throws IOException {
        ArrayList<ArrayList<Menu>> branchMenu = MenuList.getBranchMenu(menuList,branchName);
        System.out.println("Browsing Menu at:\t" + branchName);
        MenuList.displayMenu(branchMenu);
        order.displayCart();
    }


    public static void run(String branchName) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        ArrayList<Menu> menuList = Database.readMenuList();
        ArrayList<Order> orderList = Database.readOrderList();
        ArrayList<ArrayList<Menu>> branchMenu = MenuList.getBranchMenu(menuList, branchName) ;

        while (!exit) {
            displayMenuBrowsing(menuList, branchName);
            System.out.println("Enter 0 to exit the Menu");
            System.out.println("Please select which category you would like: ");
            int choiceMainMenu = scanner.nextInt();
            switch (choiceMainMenu) {
                case 1:
                    ChoiceMenu.chooseItems(branchMenu.get(0));//Set Meals
                    break;
                case 2:
                    ChoiceMenu.chooseItems(branchMenu.get(1)); //Burger
                    break;
                case 3:
                    ChoiceMenu.chooseItems(branchMenu.get(2)); // Side
                    break;
                case 4:
                    ChoiceMenu.chooseItems(branchMenu.get(3)); // Drink
                    break;
                case 5:
                    ChoiceMenu.chooseItems(branchMenu.get(branchMenu.size()-1));// Others
                    break;
                case 0:
                    exit = true;
                    System.out.println(order);
                    break;

                default:
                    System.out.println("Error, Wrong Choice please try again");
                    break;
            }
        }
    }
}