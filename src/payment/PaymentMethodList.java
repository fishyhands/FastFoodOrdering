package payment;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;

/**
 * The PaymentMethodList class manages a list of payment methods.
 */
public class PaymentMethodList {

	private static ArrayList<PaymentMethod> paymentMethodList;

	/**
	 * Initializes the PaymentMethodList by reading payment methods from the database.
	 *
	 * @throws IOException            If an I/O error occurs while reading payment methods from the database.
	 * @throws ClassNotFoundException If the class of a serialized object cannot be found.
	 */
	public PaymentMethodList() throws IOException, ClassNotFoundException {
		paymentMethodList = Database.readPaymentMethods();
	}

	/**
	 * Retrieves the list of payment methods.
	 *
	 * @return The list of payment methods.
	 */
	public static ArrayList<PaymentMethod> getPaymentMethodList() {
		return paymentMethodList;
	}

	/**
	 * Writes the payment methods to the database.
	 *
	 * @throws IOException If an I/O error occurs while writing payment methods to the database.
	 */
	public static void writePaymentMethods() throws IOException {
		Database.writePaymentMethods(paymentMethodList);
	}
}
