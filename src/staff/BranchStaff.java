package staff;

import exceptions.UnknownStaffRoleException;
import order.Order;
import order.Order_Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a branch staff member.
 */
public class BranchStaff extends Staff {

    /**
     * Constructs a new BranchStaff object.
     *
     * @param staffName      The name of the staff member.
     * @param loginID        The login ID of the staff member.
     * @param staffPassword  The password of the staff member.
     * @param role           The role of the staff member.
     * @param gender         The gender of the staff member.
     * @param age            The age of the staff member.
     * @param branch         The branch where the staff member works.
     */
    public BranchStaff(String staffName, String loginID, String staffPassword, String role, String gender, int age, String branch) {
        super(staffName, loginID, staffPassword, role, gender, age, branch);
    }

    /**
     * Displays orders for the branch where the staff member works.
     *
     * @param orderList The list of orders.
     */
    public void displayOrders(ArrayList<Order> orderList) {
        boolean found = false;
        for (Order o : orderList) {
            if (o.getBranch().equals(this.getBranch())) {
                System.out.println("Order ID\t" + "Status");
                System.out.println(o.getOrderID() + "\t" + o.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("There are no orders in this branch yet");
        }
    }

    /**
     * Selects an order based on user input.
     *
     * @param orderList The list of orders.
     * @return The selected order.
     */
    private Order orderSelection(ArrayList<Order> orderList) {
        System.out.println("Please enter the Order ID:");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        for (Order order : orderList) {
            if (order.getOrderID() == id & order.getBranch().equals(this.getBranch())) {
                return order;
            }
        }
        System.out.println("Order ID is wrong");
        return null;
    }

    /**
     * Displays details of an order for the branch where the staff member works.
     *
     * @param orderList The list of orders.
     */
    public void viewOrderDetails(ArrayList<Order> orderList) {
        displayOrders(orderList);
        Order order = null;
        while (order == null) {
            boolean found = false;
            for (Order o : orderList) {
                if (o.getBranch().equals(this.getBranch())) {
                    found = true;
                    break;
                }
            }
            if (found) {
                order = orderSelection(orderList);
                System.out.println("Details for Order ID: " + order.getOrderID());
                System.out.println("Status: " + order.getStatus());
                order.displayCart();
            } else {
                break;
            }
        }
    }

    /**
     * Processes an order for the branch where the staff member works.
     *
     * @param orderList The list of orders.
     */
    public void processOrder(ArrayList<Order> orderList) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Order> branchOrder = new ArrayList<>();
        this.displayOrders(orderList);
        for (Order o : orderList) {
            if (o.getBranch().equals(this.getBranch())) {
                branchOrder.add(o);
            }
        }
        if (branchOrder.isEmpty()) {
            System.out.println("There are currently no new orders");
            return;
        } else {
            Order order = null;
            while (order == null) {
                order = orderSelection(orderList);
            }

            System.out.println("\t\t1. Preparing Now");
            System.out.println("\t\t2. Ready");
            System.out.println("Please select the new Status: ");
            System.out.println("Enter 0 to cancel");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    order.setStatus(Order_Status.PREPARING_NOW);
                    break;

                case 2:
                    order.setStatus(Order_Status.READY);
                    order.setTime();
                    break;

                default:
                    System.out.println("Error wrong choice!");
                    break;
            }
        }
        return;
    }

    /**
     * Displays the staff menu for branch staff members.
     *
     * @throws IOException               If an I/O error occurs.
     * @throws UnknownStaffRoleException If an unknown staff role is encountered.
     * @throws ClassNotFoundException    If the class of a serialized object cannot be found.
     */
    public void staffMenu() throws IOException, UnknownStaffRoleException, ClassNotFoundException {
        StaffMainMenu.mainMenu(this);
    }
}
