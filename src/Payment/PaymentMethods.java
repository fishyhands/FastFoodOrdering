package Payment;

import java.util.Scanner;
import java.io.Serializable;


class CreditCard implements PaymentMethod, Serializable {
	
	public CreditCard() {}
	
	
	public String getName() {
		return "Credit Card";
	}

	
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
		
		//verify
		System.out.println("Verifying card details...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Payment is successful.");
	}
}

class DebitCard implements PaymentMethod, Serializable {
	
	public DebitCard() {
	}
	
	
	public String getName() {
		return "Debit Card";
	}

	
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
		
		//verify
		System.out.println("Verifying card details...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Payment is successful.");
	}
}

class OnlinePaymentPlatform implements PaymentMethod, Serializable {
	
	public OnlinePaymentPlatform() {
	}

	
	public String getName() {
		return "Online Payment Platform";
	}
	
	
	public void process() {
		
		int bank_code;
		int bank_pin;
		
		Scanner sc = new Scanner(System.in);
		
		//choose bank
		System.out.println("====== Banking Options ======");
		System.out.println("|1. OCBC                    |");
		System.out.println("|2. DBS                     |");
		System.out.println("|3. POSB                    |");
		System.out.println("=============================");
		int bankType = sc.nextInt();
	
		//for not valid payment methods
		while(bankType > 3 || bankType < 1) {
			System.out.println("Please choose valid banking option: ");
			bankType = sc.nextInt();
		}
	
		System.out.println("Enter access code: ");
		bank_code = sc.nextInt();
		System.out.println("Enter pin: ");
		bank_pin = sc.nextInt();
		
		//verify
		System.out.println("Verifying card details...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Payment is successful.");
	}
}

class otherMethods implements PaymentMethod, Serializable {
	String name;
	
	public otherMethods(String name) {
		this.name = name;
	}
	
	
	public void process() {
		
		System.out.println("Payment by "+ name + ".");
		
		//verify
		System.out.println("Please wait while payment is processing ...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Payment is successful.");
		
	}

	
	public String getName() {
		return name;
	}
	
}
