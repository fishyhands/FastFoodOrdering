package payment;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;

public class PaymentMethodList {
	private static ArrayList<PaymentMethod> paymentMethodList;
		
	public PaymentMethodList() throws IOException, ClassNotFoundException {
		paymentMethodList = Database.readPaymentMethods();
	}
	
	public static ArrayList<PaymentMethod> getPaymentMethodList() {
		return paymentMethodList;
	}
	
	public static void writePaymentMethods() throws IOException {
    	Database.writePaymentMethods(paymentMethodList);
    }
}
