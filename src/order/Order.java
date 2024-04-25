package order;

import database.Database;
import menu.Menu;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Order class represents an order made by a customer.
 * It contains details such as the order ID, branch, status, takeaway preference, items in the cart, and total sum.
 */
public class Order {
    private int orderID;
    private String branch;
    private boolean paid;
    private Order_Status status;
    private boolean takeaway;
    private HashMap<String, Integer> cart;
    private float totalSum;
    private LocalTime time;

    /**
     * Constructs a new Order object with the specified details.
     *
     * @param orderID   The ID of the order.
     * @param branch    The name of the branch where the order is placed.
     * @param paid      A boolean indicating whether the order is paid.
     * @param status    The status of the order.
     * @param takeaway  A boolean indicating whether the order is for takeaway.
     * @param cart      The items in the order cart.
     * @param totalSum  The total sum of the order.
     * @param time      The time when the order was placed.
     */
    public Order(int orderID, String branch, boolean paid, Order_Status status, boolean takeaway, HashMap<String, Integer> cart, float totalSum, LocalTime time) {
        this.orderID = orderID;
        this.branch = branch;
        this.paid = paid;
        this.status = status;
        this.takeaway = takeaway;
        this.cart = cart;
        this.totalSum = totalSum;
        this.time = time;
    }

    /**
     * Gets the ID of the order.
     *
     * @return The order ID.
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Sets the ID of the order.
     *
     * @param orderID The order ID to set.
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Gets the name of the branch where the order is placed.
     *
     * @return The branch name.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets the name of the branch where the order is placed.
     *
     * @param branch The branch name to set.
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Checks if the order is paid.
     *
     * @return True if the order is paid, false otherwise.
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets the paid status of the order.
     *
     * @param paid The paid status to set.
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * Gets the status of the order.
     *
     * @return The order status.
     */
    public Order_Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the order.
     *
     * @param status The order status to set.
     */
    public void setStatus(Order_Status status) {
        this.status = status;
    }

    /**
     * Checks if the order is for takeaway.
     *
     * @return True if the order is for takeaway, false otherwise.
     */
    public boolean isTakeaway() {
        return takeaway;
    }

    /**
     * Sets the takeaway status of the order.
     *
     * @param takeaway The takeaway status to set.
     */
    public void setTakeaway(boolean takeaway) {
        this.takeaway = takeaway;
    }

    /**
     * Gets the items in the order cart.
     *
     * @return A HashMap representing the items in the cart.
     */
    public HashMap<String, Integer> getCart() {
        return cart;
    }

    /**
     * Sets the items in the order cart.
     *
     * @param cart A HashMap representing the items to set in the cart.
     */
    public void setCart(HashMap<String, Integer> cart) {
        this.cart = cart;
    }

    /**
     * Gets the total sum of the order.
     *
     * @return The total sum of the order.
     */
    public float getTotalSum() {
        return totalSum;
    }

    /**
     * Sets the total sum of the order.
     *
     * @param totalSum The total sum to set for the order.
     */
    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }

    /**
     * Gets the time when the order was placed.
     *
     * @return The time of the order.
     */
    public LocalTime getTime() {
        return this.time.truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * Sets the time when the order was placed to the current time.
     */
    public void setTime() {
        this.time = LocalTime.now();
    }

    /**
     * Displays the items in the order cart.
     */
    public void displayCart() {
        if (this.cart.isEmpty()) {
            System.out.println("---Cart is empty---");
        } else {
            System.out.println("---Cart---");
            for (Map.Entry<String, Integer> set : this.cart.entrySet()) {
                System.out.println("Item: " + set.getKey() + " |  Quantity:\t" + set.getValue());
            }
        }
        System.out.println("----------");
    }

    /**
     * Calculates the total sum of the order based on the items in the cart and their prices.
     *
     * @return The total sum of the order.
     */
    public float calculateTotalSum() {
        float sum = 0;
        try {
            ArrayList<Menu> menu = Database.readMenuList();
            for (Map.Entry<String, Integer> set : this.cart.entrySet()) {
                for (Menu item : menu) {
                    if (set.getKey().equals(item.getName()) & item.getBranch().equals(this.branch)) {
                        sum = (set.getValue() * item.getPrice()) + sum;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        setTotalSum(sum);
        return sum;
    }

    /**
     * Formats the total sum of the order to two decimal places.
     *
     * @return The formatted total sum of the order.
     */
    public String formatTotalSum() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.totalSum);
    }
}
