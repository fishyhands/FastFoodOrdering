package Order;

import java.util.ArrayList;
import java.time.LocalTime;
import java.time.Duration;


public class OrderTimer {
    public static ArrayList<Order> timerOrder(ArrayList<Order> orderList){
         for (Order o: orderList){
             LocalTime orderTime = o.getTime();
             Duration duration = Duration.between(orderTime, LocalTime.now());
             if (duration.toSeconds() >= 60){
                 orderList.remove(o);
             }
         }
        return orderList;
    }
}
