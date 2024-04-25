package order;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;

public class OrderList {
	private static ArrayList<Order> orderList;
	
	public OrderList() throws IOException {
		orderList = Database.readOrderList();
	}
	
	public static ArrayList<Order> getOrderList() {
		return orderList;
	}
	
    public static void writeOrderList() throws IOException {
    	Database.writeOrderList(orderList);
    }
}
