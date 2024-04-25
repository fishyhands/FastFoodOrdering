package login;


import order.Order;
import order.OrderList;
import staff.Staff;
import staff.StaffList;

import java.util.ArrayList;

public class Validate {
    public static boolean validated;
    public static Staff validateStaff(String loginID, String password) {
    	ArrayList<Staff> staffList = StaffList.getStaffList();
        Staff staff_Correct = null;

        for (Staff staff : staffList) {
            String login = staff.getLoginID();
            if (login.equals(loginID)) {
                staff_Correct = staff;
                break; // Once the correct staff member is found, exit the loop
            }
        }
        if (staff_Correct == null) {
            System.out.println("User not found");
            return null; // Return null if no staff member with the given login ID is found
        }
        if(password.equals(staff_Correct.getPassword())){
            return staff_Correct;
        }else{
            System.out.println("Wrong password");
        }
        return null;
    }

    public static Order validateOrder(int orderID) {
    	ArrayList<Order> orderList = OrderList.getOrderList(); 
    	
        for (Order order: orderList){
            if (order.getOrderID() == orderID){
                return order;
            }
        }
             
        System.out.println("Order not found");
        return null;
    }
}
