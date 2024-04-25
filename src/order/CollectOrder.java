package order;

import java.io.IOException;
import java.util.ArrayList;

public class CollectOrder {
    public static void collectReadyOrder(int orderID){
        for(Order order : OrderList.getOrderList()){
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
