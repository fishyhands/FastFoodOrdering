package Order;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import Database.Database;
import Order.Order;
import Staff.Staff;
import Order.Order_Status;

public class OrderTimer {
    private Timer timer;
    private Order order;
    private ArrayList<Order> orderList;

    public OrderTimer(Order order, ArrayList<Order> orderList){
    	this.order = order;
        this.orderList = orderList;
    }

    public void startTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask(){
            public void run(){
                if(order.getStatus().equals("READY")){
                    System.out.println("Deleting Order" + order.getOrderID());
                    //remove order
                    orderList.remove(order.getOrderID());
                }
            }
        }, 60000); // 1 min timer
    }

    //if status complete, stop timer
    public void cancelTimerCompleted(){
        if(order.getStatus().equals("COMPLETE")){
            removeTimer();
        }
    }

    //cancel the timer when status changes to completed
    public void removeTimer(){
        if(timer != null){
            timer.cancel();
        }
    }
}
