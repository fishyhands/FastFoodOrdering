package Order;

import java.util.ArrayList;
import java.time.LocalTime;
import java.time.Duration;


public class OrderTimer {
    public static ArrayList<Order> timerOrder(ArrayList<Order> orderList) {
        for (Order o : orderList) {
            if (o.getStatus().equals(Order_Status.READY)) {
                LocalTime orderTime = o.getTime();
                Duration duration = Duration.between(orderTime, LocalTime.now());
                if (duration.toSeconds() >= 120) {
                    orderList.remove(o);
                }
            }

            if (o.getStatus().equals(Order_Status.COMPLETE)){
                LocalTime orderTime = o.getTime();
                Duration duration = Duration.between(orderTime, LocalTime.now());
                if (duration.toSeconds() >= 360){
                    orderList.remove(o);
                }
            }
        }
        return orderList;
    }
}
