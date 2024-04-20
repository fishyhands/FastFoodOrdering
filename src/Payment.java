package Payment;

import java.util.Scanner;

public class Payment {
	private PaymentMethod methods[];
	
	public Payment() {}
	// initialise with default payment methods
	public Payment(String paymentMethod) {
		methods = new PaymentMethod[10];
		this.methods[0] = new CreditCard();
		this.methods[1] = new DebitCard();
		this.methods[2] = new OnlinePaymentPlatform();
	}
	
	
	public void showMethods() {
		System.out.println("====== Payment Options ======");
		for (int i = 0; i < methods.length; i++) {
			System.out.println("|" + i+1 + ". " + methods[i].getName());
		}
		System.out.println("=============================");
	}
	
	
	public void addMethod() {
		Scanner sc = new Scanner(System.in);
		
		showMethods();
		
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
					for (int i = 0; i < methods.length; i++) {
						if (methods[i].getName().compareTo("Credit Card") == 0)
							System.out.println("Credit Card payment method already exists!");
							break;
					}
					
					// else, make it available
					this.methods[methods.length] = new CreditCard();
					System.out.println("Payment methods updated!");
					showMethods();
					break;
				case 2:
					// if Debit Card is available, break
					for (int i = 0; i < methods.length; i++) {
						if (methods[i].getName().compareTo("Debit Card") == 0)
							System.out.println("Debit Card payment method already exists!");
							break;
					}
					
					// else, make it available
					this.methods[methods.length] = new DebitCard();
					System.out.println("Payment methods updated!");
					showMethods();
					break;
				case 3:
					// if Online Payment Platforms are available, break
					for (int i = 0; i < methods.length; i++) {
						if (methods[i].getName().compareTo("Online Payment Platform") == 0)
							System.out.println("Online Payment Platform payment method already exists!");
							break;
					}
					
					// else, make it available
					this.methods[methods.length] = new OnlinePaymentPlatform();
					System.out.println("Payment methods updated!");
					showMethods();
					break;
				case 4:
					System.out.print("Please enter name of new payment method: ");
					String name = sc.next();
					
					// if already available, break
					for (int i = 0; i < methods.length; i++) {
						if (methods[i].getName().equalsIgnoreCase(name))
							System.out.println(name + "payment method already exists!");
							break;
					}
					
					// else, make it available
					this.methods[methods.length] = new otherMethods(name);
					System.out.println("Payment methods updated!");
					showMethods();
					break;
				default:
					System.out.println("Invalid choice!");
			}
		} while (choice < 1 || choice > methods.length);
	}
	
	
	public void removeMethod() {
		Scanner sc = new Scanner(System.in);
		
		showMethods();
		
		int choice = -1;
		int size = methods.length;
		
		do {
			System.out.println("Which payment method would you like to remove?");
			choice = sc.nextInt();
			
			if (choice < 1 || choice > size)
				System.out.println("Invalid input!");
			else {
				for (int i = choice-1; i < size; i++)
					methods[i] = methods[i+1];
				methods[size-1] = null;
				System.out.println("Payment methods updated!");
				showMethods();
			}
		} while (choice < 1 || choice > size);	
	}
	
	
	public void pay() {
		Scanner sc = new Scanner(System.in);

		showMethods();
		
		int paymentType = -1;
		
		do {
			System.out.println("Please enter selection: ");
			paymentType = sc.nextInt();
		
			//for invalid choice
			if (paymentType > methods.length || paymentType < 1)
				System.out.println("Invalid input!");
			else {
				methods[paymentType-1].process();
			}
		} while (paymentType > methods.length || paymentType < 1);
	}
}
