package Order;

import java.io.IOException;
import java.util.ArrayList;

public class CollectOrder {
    public static void collectReadyOrder(ArrayList<Order> orders, int orderID){
        for(Order order : orders){
            if(order.getOrderID() == orderID && order.getStatus() != Order_Status.READY){
                System.out.println("Order " + orderID + " is not ready for collection.");
                break;
            }
            if(order.getOrderID() == orderID && order.getStatus() == Order_Status.READY){
                collectOrder(order);
                break;
            }
        }

    }


    public static void collectOrder(Order order){
        System.out.println("Order " + order.getOrderID() + " has been collected.");
        order.setStatus(Order_Status.COMPLETE);
    }
}
