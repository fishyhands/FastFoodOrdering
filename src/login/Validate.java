package login;

import order.Order;
import order.OrderList;
import staff.Staff;
import staff.StaffList;

import java.util.ArrayList;

/**
 * The Validate class provides methods for validating user credentials and orders.
 * It validates staff login credentials and order IDs.
 */
public class Validate {

    /**
     * Validates the login credentials of a staff member.
     *
     * @param loginID  The login ID entered by the user.
     * @param password The password entered by the user.
     * @return The Staff object if the credentials are valid, otherwise null.
     */
    public static Staff validateStaff(String loginID, String password) {
        ArrayList<Staff> staffList = StaffList.getStaffList();
        Staff staffCorrect = null;

        for (Staff staff : staffList) {
            String login = staff.getLoginID();
            if (login.equals(loginID)) {
                staffCorrect = staff;
                break; // Once the correct staff member is found, exit the loop
            }
        }
        if (staffCorrect == null) {
            System.out.println("User not found");
            return null; // Return null if no staff member with the given login ID is found
        }
        if (password.equals(staffCorrect.getPassword())) {
            return staffCorrect;
        } else {
            System.out.println("Wrong password");
        }
        return null;
    }

    /**
     * Validates an order by its ID.
     *
     * @param orderID The ID of the order to validate.
     * @return The Order object if the order is found, otherwise null.
     */
    public static Order validateOrder(int orderID) {
        ArrayList<Order> orderList = OrderList.getOrderList();

        for (Order order : orderList) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }

        System.out.println("Order not found");
        return null;
    }
}
