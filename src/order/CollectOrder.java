package order;

/**
 * The CollectOrder class provides functionality for collecting orders.
 * It allows users to check if an order is ready for collection and collect the order if it is ready.
 */
public class CollectOrder {

    /**
     * Checks if the specified order is ready for collection and collects it if it is ready.
     *
     * @param orderID The ID of the order to collect.
     */
    public static void collectReadyOrder(int orderID) {
        for (Order order : OrderList.getOrderList()) {
            if (order.getOrderID() == orderID && order.getStatus() != Order_Status.READY) {
                System.out.println("Order " + orderID + " is not ready for collection.");
                break;
            }
            if (order.getOrderID() == orderID && order.getStatus() == Order_Status.READY) {
                collectOrder(order);
                break;
            }
        }
    }

    /**
     * Collects the specified order.
     *
     * @param order The order to be collected.
     */
    public static void collectOrder(Order order) {
        System.out.println("Order " + order.getOrderID() + " has been collected.");
        order.setStatus(Order_Status.COMPLETE);
    }
}
