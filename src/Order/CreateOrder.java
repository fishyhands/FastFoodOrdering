package Order;


import Database.Database;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CreateOrder {    
    public static Order createOrder(String branch) throws IOException {
        ArrayList<Order> orderArrayList = Database.readOrderList();
        Scanner sc = new Scanner(System.in);
        boolean takeaway = false;
        int orderID;
        if (orderArrayList.isEmpty()){
            orderID = 1;
        }else{
            orderID = orderArrayList.get(orderArrayList.size()-1).getOrderID() + 1;
        }
        System.out.println("Do you want to takeaway the order?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                takeaway = true;
                break;
            case 2:
                break;
            default:
                System.out.println("Wrong entry. Please enter again");
        }
        HashMap<String,Integer> cart = new HashMap<>();
        return new Order(orderID, branch, false, Order_Status.PENDING, takeaway, cart, 0, LocalTime.now());
    }
}
