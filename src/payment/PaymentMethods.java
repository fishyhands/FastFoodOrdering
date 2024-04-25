package payment;

import java.io.Serializable;
import java.util.Scanner;

/**
 * The CreditCard class represents a credit card payment method.
 */
class CreditCard implements PaymentMethod, Serializable {

	/**
	 * Constructs a new CreditCard object.
	 */
	public CreditCard() {}

	/**
	 * Retrieves the name of the payment method.
	 *
	 * @return The name of the payment method.
	 */
	public String getName() {
		return "Credit Card";
	}

	/**
	 * Processes the credit card payment.
	 */
	public void process() {
		while (true) {
			try {
				long card_no;
				String card_exp;
				int card_cvv;

				Scanner sc = new Scanner(System.in);
				System.out.println("Enter card number: ");
				card_no = sc.nextLong();
				System.out.println("Enter card expiration date mm//yy: ");
				card_exp = sc.next();
				System.out.println("Enter CVV number: ");
				card_cvv = sc.nextInt();

				// Verify
				System.out.println("Verifying card details...");

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Payment is successful.");
				break;

			} catch (Exception e) {
				System.out.println("Please enter a valid input");
			}
		}
	}
}

/**
 * The DebitCard class represents a debit card payment method.
 */
class DebitCard implements PaymentMethod, Serializable {

	/**
	 * Constructs a new DebitCard object.
	 */
	public DebitCard() {}

	/**
	 * Retrieves the name of the payment method.
	 *
	 * @return The name of the payment method.
	 */
	public String getName() {
		return "Debit Card";
	}

	/**
	 * Processes the debit card payment.
	 */
	public void process() {
		long card_no;
		String card_exp;
		int card_cvv;

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter card number: ");
		card_no = sc.nextLong();
		System.out.println("Enter card expiration date mm//yy: ");
		card_exp = sc.next();
		System.out.println("Enter CVV number: ");
		card_cvv = sc.nextInt();

		// Verify
		System.out.println("Verifying card details...");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Payment is successful.");
	}
}

/**
 * The OnlinePaymentPlatform class represents an online payment platform payment method.
 */
class OnlinePaymentPlatform implements PaymentMethod, Serializable {

	/**
	 * Constructs a new OnlinePaymentPlatform object.
	 */
	public OnlinePaymentPlatform() {}

	/**
	 * Retrieves the name of the payment method.
	 *
	 * @return The name of the payment method.
	 */
	public String getName() {
		return "Online Payment Platform";
	}

	/**
	 * Processes the online payment platform payment.
	 */
	public void process() {
		int bank_code;
		int bank_pin;

		Scanner sc = new Scanner(System.in);

		// Choose bank
		System.out.println("====== Banking Options ======");
		System.out.println("|1. OCBC                    |");
		System.out.println("|2. DBS                     |");
		System.out.println("|3. POSB                    |");
		System.out.println("=============================");
		int bankType = sc.nextInt();

		// For not valid payment methods
		while (bankType > 3 || bankType < 1) {
			System.out.println("Please choose valid banking option: ");
			bankType = sc.nextInt();
		}

		System.out.println("Enter access code: ");
		bank_code = sc.nextInt();
		System.out.println("Enter pin: ");
		bank_pin = sc.nextInt();

		// Verify
		System.out.println("Verifying card details...");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Payment is successful.");
	}
}

/**
 * The OtherMethods class represents other payment methods.
 */
class OtherMethods implements PaymentMethod, Serializable {
	String name;

	/**
	 * Constructs a new OtherMethods object with the specified name.
	 *
	 * @param name The name of the payment method.
	 */
	public OtherMethods(String name) {
		this.name = name;
	}

	/**
	 * Processes the payment using other methods.
	 */
	public void process() {
		System.out.println("Payment by " + name + ".");

		// Verify
		System.out.println("Please wait while payment is processing ...");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Payment is successful.");
	}

	/**
	 * Retrieves the name of the payment method.
	 *
	 * @return The name of the payment method.
	 */
	public String getName() {
		return name;
	}

}
