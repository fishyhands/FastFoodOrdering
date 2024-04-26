package payment;

import static menu.MenuBrowsing.currentOrder;

import java.util.ArrayList;

/**
 * The PaymentMainMenu class provides methods for displaying and managing payment options in the main menu.
 */
public class PaymentMainMenu {

    /**
     * Initializes the payment methods if the list is empty.
     *
     * @param paymentMethods The list of payment methods to be initialized.
     * @return The list of payment methods after initialization.
     */
    private static ArrayList<PaymentMethod> initializePayment(ArrayList<PaymentMethod> paymentMethods){
        if (paymentMethods.isEmpty()){
            paymentMethods.add(new CreditCard());
            paymentMethods.add(new DebitCard());
            paymentMethods.add(new OnlinePaymentPlatform());
        }
        return paymentMethods;
    }

    /**
     * Displays the payment menu and initiates the payment process.
     *
     * @param paymentMethods The list of payment methods available for selection.
     */
    public static void PaymentMenu(ArrayList<PaymentMethod> paymentMethods){
        initializePayment(paymentMethods);
        Payment.pay(paymentMethods);
        currentOrder.setPaid(true);
    }  
}

