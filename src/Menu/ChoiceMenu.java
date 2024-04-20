package Menu;

import java.util.ArrayList;
import java.util.Scanner;
import static Menu.MenuBrowsing.order;

public class ChoiceMenu {
    private static String itemName;
    private static int quantity;

    public static void chooseItems(ArrayList<Menu> menuCategory) {
        int size = menuCategory.size();
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + menuCategory.get(i).getName() + "\t" + menuCategory.get(i).getPrice());
        }
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        itemName = menuCategory.get(choice-1).getName();
        System.out.println("Enter the quantity: ");
        quantity = sc.nextInt();

        if (order.getCart().containsKey(itemName)){
            int addQuantity = order.getCart().get(itemName) + quantity;
            order.getCart().put(itemName, addQuantity);
            System.out.println("Added to cart");
        } else {
            order.getCart().put(itemName, quantity);
            System.out.println("Added to cart");
        }
    }

}
