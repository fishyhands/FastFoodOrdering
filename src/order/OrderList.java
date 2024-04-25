package order;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;

/**
 * The OrderList class represents a list of orders.
 * It provides methods for retrieving and writing order data.
 */
public class OrderList {
	/** The list of orders. */
	private static ArrayList<Order> orderList;

	/**
	 * Initializes the OrderList by reading order data from the database.
	 *
	 * @throws IOException If an I/O error occurs while reading order data.
	 */
	public OrderList() throws IOException {
		orderList = Database.readOrderList();
	}

	/**
	 * Gets the list of orders.
	 *
	 * @return The list of orders.
	 */
	public static ArrayList<Order> getOrderList() {
		return orderList;
	}

	/**
	 * Writes the list of orders to the database.
	 *
	 * @throws IOException If an I/O error occurs while writing order data.
	 */
	public static void writeOrderList() throws IOException {
		Database.writeOrderList(orderList);
	}
}
