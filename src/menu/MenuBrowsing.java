package menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import order.CreateOrder;
import order.Order;

/**
 * The MenuBrowsing class provides functionality for browsing and selecting menu items.
 * It allows users to view the menu of a specific branch, choose items, and add them to an order cart.
 */
public class MenuBrowsing {
    public static Order currentOrder;

    /**
     * Displays the menu of a specified branch and allows the user to select items to add to the order cart.
     *
     * @param branchName The name of the branch whose menu is to be displayed.
     * @throws IOException If an I/O error occurs while reading the menu data.
     */
    private static void displayMenuBrowsing(String branchName) throws IOException {
        ArrayList<ArrayList<Menu>> branchMenu = MenuList.getBranchMenu(branchName);
        System.out.println("Browsing Menu at:\t" + branchName);
        MenuList.displayMenu(branchMenu);
        currentOrder.displayCart();
    }

    /**
     * Runs the menu browsing process for a specified branch.
     *
     * @param branchName The name of the branch to browse the menu for.
     * @throws IOException If an I/O error occurs while reading the menu data.
     */
    public static void run(String branchName) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        currentOrder = CreateOrder.createOrder(branchName);
        ArrayList<ArrayList<Menu>> branchMenu = MenuList.getBranchMenu(branchName);

        // no items added to cart yet
        try {
            displayMenuBrowsing(branchName);
            System.out.println("Enter 0 to exit the Menu and pay");
            System.out.println("Please select which category you would like: ");
            int choiceMainMenu = scanner.nextInt();
            if (choiceMainMenu == 0) {
                exit = true;
                currentOrder.calculateTotalSum();
                System.out.println("Total: " + currentOrder.formatTotalSum());
            } else if (choiceMainMenu < 0 || choiceMainMenu > branchMenu.size()) {
                System.out.println("Invalid choice, please try again");
            } else if (branchMenu.get(choiceMainMenu - 1).size() <= 0) {
                System.out.println("No available items in that category, please pick another one");
            } else {
                ChoiceMenu.chooseItems(branchMenu.get(choiceMainMenu - 1));
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input");
            scanner.nextLine();
        }

        while (!exit) {
            try {
                // subsequent items
                System.out.println("Enter 0 to exit the Menu and pay");
                // ADDED - option to add, remove, edit items in cart
                System.out.println("1. Continue ordering\n2. Empty cart.");
                int opt = scanner.nextInt();
                switch (opt) {
                    case 0:
                        exit = true;
                        float totalSum = currentOrder.calculateTotalSum();
                        String formattedValue = String.format("%.2f", totalSum);
                        System.out.println("Total: " + formattedValue);
                        break;
                    case 1:
                        displayMenuBrowsing(branchName);
                        System.out.println("Please select which category you would like: ");
                        int choiceMainMenu = scanner.nextInt();

                        if (choiceMainMenu < 0 || choiceMainMenu > branchMenu.size()) {
                            System.out.println("Invalid choice, please try again");
                        } else if (branchMenu.get(choiceMainMenu - 1).size() <= 0) {
                            System.out.println("No available items in that category, please pick another one");
                        } else {
                            ChoiceMenu.chooseItems(branchMenu.get(choiceMainMenu - 1));
                        }
                        break;
                    case 2:
                        // "empty cart"
                        // create new hash map
                        HashMap<String, Integer> newCart = new HashMap<>();
                        // replace with empty cart
                        currentOrder.setCart(newCart);
                        System.out.println("Cart emptied!");
                        break;
                    default:
                        System.out.println("Invalid input!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input");
                scanner.nextLine();
            }
        }
    }
}
