package order;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The OrderTimer class provides functionality for managing order timers.
 * It allows for the removal of orders that have exceeded certain time limits.
 */
public class OrderTimer {

    /**
     * Removes orders from the list that have been completed and are ready to collect but not collected for a while.
     *
     * @param orderList The list of orders to be checked.
     */
    public static void timerOrder(ArrayList<Order> orderList) {
        // Create a list to hold orders to remove
        ArrayList<Order> ordersToRemove = new ArrayList<>();

        // Iterate through the orderList
        for (Order o : orderList) {
            if (o.getStatus().equals(Order_Status.READY)) {
                LocalTime orderTime = o.getTime();
                Duration duration = Duration.between(orderTime, LocalTime.now());
                if (duration.toSeconds() >= 30) {
                    System.out.println("Order deleted: Time limit exceeded for order: " + o.getOrderID());
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
    }
}
