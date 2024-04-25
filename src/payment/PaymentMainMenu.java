package payment;

import static menu.MenuBrowsing.order;

import java.util.ArrayList;


public class PaymentMainMenu {
    private static ArrayList<PaymentMethod> initializePayment(ArrayList<PaymentMethod> paymentMethods){
        if (paymentMethods.isEmpty()){
            paymentMethods.add(new CreditCard());
            paymentMethods.add(new DebitCard());
            paymentMethods.add(new OnlinePaymentPlatform());
        }
        return paymentMethods;
    }

    public static void PaymentMenu(ArrayList<PaymentMethod> paymentMethods){
        initializePayment(paymentMethods);
        Payment.pay(paymentMethods);
        order.setPaid(true);
    }  
}

