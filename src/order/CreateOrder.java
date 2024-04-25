package order;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The CreateOrder class provides functionality for creating new orders.
 * It allows users to create a new order with specified details such as takeaway preference and items in the order cart.
 */
public class CreateOrder {

    /**
     * Creates a new order for the specified branch.
     *
     * @param branch The name of the branch for which the order is created.
     * @return The newly created Order object.
     * @throws IOException If an I/O error occurs while creating the order.
     */
    public static Order createOrder(String branch) throws IOException {
        ArrayList<Order> orderArrayList = OrderList.getOrderList();
        Scanner sc = new Scanner(System.in);
        boolean takeaway = false;
        boolean cont = true;
        int orderID;
        if (orderArrayList.isEmpty()) {
            orderID = 1;
        } else {
            orderID = orderArrayList.get(orderArrayList.size() - 1).getOrderID() + 1;
        }
        while (cont) {
            System.out.println("Do you want to takeaway the order?");
            System.out.println("1) Yes");
            System.out.println("2) No");
            String choice = sc.next();
            switch (choice) {
                case "1":
                    takeaway = true;
                    cont = false;
                    break;
                case "2":
                    cont = false;
                    break;
                default:
                    System.out.println("Wrong entry. Please enter again");
            }
        }
        HashMap<String, Integer> cart = new HashMap<>();
        return new Order(orderID, branch, false, Order_Status.PENDING, takeaway, cart, 0, LocalTime.now());
    }
}
