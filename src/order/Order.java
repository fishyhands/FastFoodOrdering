package order;


import database.Database;
import menu.Menu;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Order {
    private int orderID;
    private String branch;
    private boolean paid;
    private Order_Status status;
    private boolean takeaway;
    private HashMap<String, Integer> cart;
    private float totalSum;
    private LocalTime time;


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

    public LocalTime getTime(){return this.time.truncatedTo(ChronoUnit.SECONDS);}

    public void setTime(){this.time = LocalTime.now();}

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Order_Status getStatus() {
        return status;
    }

    public void setStatus(Order_Status status) {
        this.status = status;
    }

    public boolean isTakeaway() {
        return takeaway;
    }

    public void setTakeaway(boolean takeaway) {
        this.takeaway = takeaway;
    }

    public HashMap<String, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, Integer> cart) {
        this.cart = cart;
    }

    public float getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }

    public void displayCart(){
        if (this.cart.isEmpty()){
            System.out.println("---Cart is empty---");
        }else {
            System.out.println("---Cart---");
            for (Map.Entry<String, Integer> set : this.cart.entrySet()) {
                System.out.println("Item: " + set.getKey() + " |  Quantity:\t" + set.getValue());
            }
        }   System.out.println("----------");
    }
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
    public String formatTotalSum() {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(this.totalSum);
    }
}
