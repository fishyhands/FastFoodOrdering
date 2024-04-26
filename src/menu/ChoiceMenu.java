package menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.IndexOutOfBoundsException;

import static menu.MenuBrowsing.currentOrder;

/**
 * The ChoiceMenu class provides functionality for choosing menu items and quantities.
 * It allows users to select items from a given menu category and add them to the order cart.
 */
public class ChoiceMenu {
    private static String itemName;
    private static int quantity;

    /**
     * Allows the user to choose items from a given menu category and add them to the order cart.
     *
     * @param menuCategory The list of Menu objects representing the menu category to choose from.
     */
    public static void chooseItems(ArrayList<Menu> menuCategory) {
        while (true) {
            try {
                int size = menuCategory.size();
                if (size == 0) {
                    System.out.println("There are currently no items in this menu. Please choose another item.");
                    break;
                } else {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("0. Back");
                    for (int i = 0; i < size; i++) {
                        System.out.println((i + 1) + ". " + menuCategory.get(i).getName() + "\t" + menuCategory.get(i).getPrice());
                    }

                    System.out.println("Enter your choice: ");
                    int choice = sc.nextInt();
                    if (choice == 0) {
                        break;
                    } else {
                        if (menuCategory.get(choice - 1).isAvailable()) {
                            itemName = menuCategory.get(choice - 1).getName();
                            System.out.println("Enter the quantity: ");
                            quantity = sc.nextInt();

                            if (currentOrder.getCart().containsKey(itemName)) {
                                int addQuantity = currentOrder.getCart().get(itemName) + quantity;
                                currentOrder.getCart().put(itemName, addQuantity);
                                System.out.println("Added to cart");
                                currentOrder.displayCart();
                            } else {
                            	currentOrder.getCart().put(itemName, quantity);
                                System.out.println("Added to cart");
                                currentOrder.displayCart();
                            }
                            break;
                        } else {
                            System.out.println("Item is not available");
                        }
                    }

                }
            } catch (IndexOutOfBoundsException | InputMismatchException ex) {
                System.out.println("Please enter a valid input");
            }
        }
    }

}
