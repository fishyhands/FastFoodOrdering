package Staff;

import Order.Order;
import Order.Order_Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BranchStaff extends Staff {
	
	public BranchStaff(String staffName, String loginID, String staffPassword, String role, String gender, int age, String branch) {
		super(staffName, loginID, staffPassword, role, gender, age, branch);
	}

    public void displayOrders(ArrayList<Order> orderList){
        boolean found = false;
        for (Order o: orderList){
            if (o.getBranch().equals(this.getBranch())){
                System.out.println("Order ID\t" + "Status");
                System.out.println(o.getOrderID() + "\t" + o.getStatus());
                found = true;
            }
        }
        if (!found){
            System.out.println("There are no orders in this branch yet");
        }
    }

    private Order orderSelection(ArrayList<Order> orderList){
        System.out.println("Please enter the Order ID:");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        for (Order o : orderList){
            if (o.getOrderID() == id & o.getBranch().equals(this.getBranch())){
                return o;
            } else if (o.getOrderID() == id & !o.getBranch().equals(this.getBranch())) {
                System.out.println("Order is not in this branch, try again");
            }
        }
        System.out.println("Order ID is wrong");
        return null;
    }

    public void viewOrderDetails(ArrayList<Order> orderList){
        Order order = null;
        while(order == null){
            order = orderSelection(orderList);
        }
        System.out.println("Details for Order ID: " + order.getOrderID());
        System.out.println("Status: " + order.getStatus());
        order.displayCart();
    }

    public ArrayList<Order> processOrder(ArrayList<Order> orderList){
        Scanner sc = new Scanner(System.in);
        ArrayList<Order> branchOrder = new ArrayList<>();
        this.displayOrders(orderList);
        for (Order o: orderList){
            if (o.getBranch().equals(this.getBranch())){
                branchOrder.add(o);
            }
        }
        if (branchOrder.isEmpty()){
            System.out.println("There are currently no new Orders");
            return null;
        }
        else{
            Order order = null;
            while (order == null){
                order = orderSelection(orderList);
            }

            System.out.println("\t\t1. Preparing Now");
            System.out.println("\t\t2. Ready");
            System.out.println("Please select the new Status: ");
            System.out.println("Enter 0 to cancel");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    order.setStatus(Order_Status.PREPARING_NOW);
                    break;

                case 2:
                    order.setStatus(Order_Status.READY);
                    break;

                default:
                    System.out.println("Error wrong choice!");
                    break;
            }
        }
        return orderList;
    }

}
