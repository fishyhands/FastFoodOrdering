package login;

//Imports all the required classes
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import branch.BranchList;
import exceptions.UnknownStaffRoleException;
import menu.MenuList;
import order.OrderList;
import order.OrderMainMenu;
import payment.PaymentMethodList;
import staff.Staff;
import staff.StaffList;

/**
 * The Login class provides functionality for user login and navigation based on user type.
 * It allows users to log in as either customers or staff members and perform appropriate actions.
 */
public class Login {

	/**
	 * The main method serves as the entry point for the application.
	 * It initializes necessary data structures and handles user login and navigation.
	 *
	 * @param args The command-line arguments (not used).
	 * @throws IOException if an I/O error occurs while reading or writing data files.
	 */
	public static void main(String[] args) throws IOException{

		try {
			// Initialize data structures
			new StaffList();
			new OrderList();
			new MenuList();
			new BranchList();
			new PaymentMethodList();

			// Select user type: Customer or Staff
			Scanner sc = new Scanner(System.in);
			System.out.println("======= Login Options =======");
			System.out.println("|1. Customer                |");
			System.out.println("|2. Staff                   |");
			System.out.println("=============================");
			System.out.println("Please enter selection: ");
			int userType = sc.nextInt();

			// Catch if user input is out of range
			while (userType > 2 || userType < 1) {
				throw new InputMismatchException();
			}

			// Perform action based on user type
			switch (userType) {
				case 1:
					OrderMainMenu.main(null);
					break;
				case 2:
					System.out.println("Enter username");
					String loginID = sc.next().trim();

					System.out.println("Enter password");
					String password = sc.next().trim();

					// Validate staff credentials
					Staff loggedInStaff = Validate.validateStaff(loginID, password);
					if (loggedInStaff == null) {
						System.out.println("Invalid login credentials!");
					}
					else {
						loggedInStaff.staffMenu();
					}
					break;
			}

			// Close scanner
			sc.close();

			// Catch various exceptions that the program might throw
		} catch (UnknownStaffRoleException ex) {
			System.out.println("UnknownStaffRoleException: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("IOException: " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException: " + ex.getMessage());
		} catch (InputMismatchException ex){
			System.out.println("Please enter a valid input");
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			// Write data to files
			StaffList.writeStaffList();
			BranchList.writeBranchList();
			OrderList.writeOrderList();
			MenuList.writeMenuList();
			PaymentMethodList.writePaymentMethods();
		}

		// Output an empty line for better readability
		System.out.println();
	}
}
