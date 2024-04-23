package Payment.Order;

import java.util.Timer;
import java.util.TimerTask;
import Database.Database;
import Order.Order;

public class OrderTimer {
    private Timer timer;
    private Order order;

    public OrderTimer(Order order){
        this.order = order;
    }

    public void startTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask){
            public void run(){
                if(order.getStatus().equals("READY")){
                    System.out.println("Deleting Order" + order.getOrderID());
                    //remove order
                    Database.removeOrder(order.getOrderID());
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
