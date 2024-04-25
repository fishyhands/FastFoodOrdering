package staff;

import exceptions.UnknownStaffRoleException;
import order.OrderList;
import order.OrderTimer;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the main menu for branch staff members.
 */
public class StaffMainMenu {
    /**
     * Displays the main menu for a branch staff member.
     *
     * @param staff The branch staff member.
     * @throws IOException               If an I/O error occurs.
     * @throws UnknownStaffRoleException If the staff role is unknown.
     */
    public static void mainMenu(BranchStaff staff) throws IOException, UnknownStaffRoleException {
        Scanner sc = new Scanner(System.in);
        int staffchoice;
        do {
            OrderTimer.timerOrder(OrderList.getOrderList()); // Remove the Orders that have expired
            System.out.println("\nManage Orders\n-------------\n1 to display new orders\n2 to view the details of a particular order\n3 to process order\n\nAccount\n-------------\n4 to change password\n5 to logout\n\nEnter choice");
            staffchoice = sc.nextInt();
            if (staffchoice == 1) {
                staff.displayOrders(OrderList.getOrderList());
            } else if (staffchoice == 2) {
                if (OrderList.getOrderList().isEmpty()) {
                    System.out.println("No new Orders");
                }
                staff.viewOrderDetails(OrderList.getOrderList());
            } else if (staffchoice == 3) {
                staff.processOrder(OrderList.getOrderList());
            } else if (staffchoice == 4) {
                System.out.println("Enter new password");
                String pwd = sc.next();
                sc.nextLine();
                staff.setPassword(pwd);
            } else if (staffchoice == 5) {
                System.out.println("Logging out");
            } else {
                System.out.println("Invalid option");
            }
        } while (staffchoice != 5);
    }
}
