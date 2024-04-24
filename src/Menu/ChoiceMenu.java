package Menu;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.IndexOutOfBoundsException;
import java.util.InputMismatchException;
import static Menu.MenuBrowsing.order;

public class ChoiceMenu {
    private static String itemName;
    private static int quantity;

    public static void chooseItems(ArrayList<Menu> menuCategory){
        while(true){
            try{
                int size = menuCategory.size();
                if(size ==0){
                    System.out.println("There are currently no items in this menu. please choose another item");
                    break;
                }else{
                    Scanner sc = new Scanner(System.in);
                    System.out.println("0. Back");
                    for (int i = 0; i < size; i++) {
                        System.out.println((i + 1) + ". " + menuCategory.get(i).getName() + "\t" + menuCategory.get(i).getPrice());
                    }
    
                    System.out.println("Enter your choice: ");
                    int choice = sc.nextInt();
                    if (choice==0){
                        break;
                    }else{
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
                        break;
                    }
                        
                }
            }catch(IndexOutOfBoundsException ex){
                System.out.println("please enter a valid input");
            }catch(InputMismatchException ex){
                System.out.println("please enter a valid input");
            }
        }
    }

}
