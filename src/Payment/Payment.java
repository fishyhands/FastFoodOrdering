package Payment;

import java.util.ArrayList;
import java.util.Scanner;

public class Payment {
	public Payment() {}
	// initialise with default payment methods

	public static void showMethods(ArrayList<? extends PaymentMethod> paymentMethods) {
		System.out.println("====== Payment Options ======");
		for (int i = 0; i < paymentMethods.size(); i++) {
			System.out.println("|" + (i+1) + ". " + paymentMethods.get(i).getName());
		}
		System.out.println("=============================");
	}
	
	
	public static void addMethod(ArrayList<PaymentMethod> paymentMethods) {
		Scanner sc = new Scanner(System.in);
		
		showMethods(paymentMethods);
		
		int choice = -1;
		
		do {
			
			System.out.println("Which payment method would you like to add?");
			System.out.println("|1. Credit Card");
			System.out.println("|2. Debit Card");
			System.out.println("|3. Online Payment Platforms");
			System.out.println("|4. Others");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					// if Credit Card is available, break
					for (int i = 0; i < paymentMethods.size(); i++) {
						if (paymentMethods.get(i).getName().compareTo("Credit Card") == 0) {
							System.out.println("Credit Card payment method already exists!");
							break;
						}
					}
					
					// else, make it available
					CreditCard newCC = new CreditCard();
					paymentMethods.add(newCC);
					System.out.println("Payment methods updated!");
					showMethods(paymentMethods);
					break;
				case 2:
					// if Debit Card is available, break
					for (int i = 0; i < paymentMethods.size(); i++) {
						if (paymentMethods.get(i).getName().compareTo("Debit Card") == 0) {
							System.out.println("Debit Card payment method already exists!");
							break;
						}
					}
					
					// else, make it available
					DebitCard newDC = new DebitCard();
					paymentMethods.add(newDC);
					System.out.println("Payment methods updated!");
					showMethods(paymentMethods);
					break;
				case 3:
					// if Online Payment Platforms are available, break
					for (int i = 0; i < paymentMethods.size(); i++) {
						if (paymentMethods.get(i).getName().compareTo("Online Payment Platform") == 0) {
							System.out.println("Online Payment Platform payment method already exists!");
							break;
						}
					}
					
					// else, make it available
					OnlinePaymentPlatform newOPP = new OnlinePaymentPlatform();
					paymentMethods.add(newOPP);
					System.out.println("Payment methods updated!");
					showMethods(paymentMethods);
					break;
				case 4:
					System.out.print("Please enter name of new payment method: ");
					String name = sc.next();
					
					// if already available, break
					for (int i = 0; i < paymentMethods.size(); i++) {
						if (paymentMethods.get(i).getName().equalsIgnoreCase(name)) {
							System.out.println(name + "payment method already exists!");
							break;
						}
					}
					
					// else, make it available
					otherMethods newOM = new otherMethods(name);
					paymentMethods.add(newOM);
					System.out.println("Payment methods updated!");
					showMethods(paymentMethods);
					break;
				default:
					System.out.println("Invalid choice!");
			}
		} while (choice < 1 || choice > paymentMethods.size());
	}
	
	
	public static void removeMethod(ArrayList<PaymentMethod> paymentMethods) {
		Scanner sc = new Scanner(System.in);
		
		showMethods(paymentMethods);
		
		int choice = -1;
		int size = paymentMethods.size();
		
		do {
			System.out.println("Which payment method would you like to remove?");
			choice = sc.nextInt();
			
			if (choice < 1 || choice > size)
				System.out.println("Invalid input!");
			else {
				paymentMethods.remove(choice-1);
				showMethods(paymentMethods);
			}
		} while (choice < 1 || choice > size);	
	}
	
	
	public static void pay(ArrayList<PaymentMethod> paymentMethods) {
		Scanner sc = new Scanner(System.in);

		showMethods(paymentMethods);
		
		int paymentType = -1;
		
		do {
			System.out.println("Please enter selection: ");
			paymentType = sc.nextInt();
		
			//for invalid choice
			if (paymentType > paymentMethods.size() || paymentType < 1)
				System.out.println("Invalid input!");
			else {
				paymentMethods.get(paymentType-1).process();
			}
		} while (paymentType > paymentMethods.size() || paymentType < 1);
	}
}
