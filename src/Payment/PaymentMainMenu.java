package Payment;

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


}
