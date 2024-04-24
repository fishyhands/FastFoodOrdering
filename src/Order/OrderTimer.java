package Order;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;


public class OrderTimer {
    public static ArrayList<Order> timerOrder(ArrayList<Order> orderList) {
        // Create a list to hold orders to remove
        ArrayList<Order> ordersToRemove = new ArrayList<>();

        // Iterate through the orderList
        for (Order o : orderList) {
            if (o.getStatus().equals(Order_Status.READY)) {
                LocalTime orderTime = o.getTime();
                Duration duration = Duration.between(orderTime, LocalTime.now());
                if (duration.toSeconds() >= 120) {
                    ordersToRemove.add(o); // Add order to remove list
                }
            }

            if (o.getStatus().equals(Order_Status.COMPLETE)){
                LocalTime orderTime = o.getTime();
                Duration duration = Duration.between(orderTime, LocalTime.now());
                if (duration.toSeconds() >= 360){
                    ordersToRemove.add(o); // Add order to remove list
                }
            }
        }

        // Remove orders from orderList
        orderList.removeAll(ordersToRemove);

        return orderList;
    }
}

