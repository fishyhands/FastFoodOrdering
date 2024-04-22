package Login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Order.OrderMainMenu;
import Staff.Staff;
import Staff.StaffMainMenu;


public class Login {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        // select customer or Staff
        Scanner sc = new Scanner(System.in);
        System.out.println("======= Login Options =======");
        System.out.println("|1. Customer                |");
        System.out.println("|2. Staff                   |");
        System.out.println("=============================");
        System.out.println("Please enter selection: ");
        int userType = sc.nextInt();

        while (userType > 2 || userType < 1) {
            System.out.println("Please choose valid login option: ");
            userType = sc.nextInt();
        }

        switch (userType) {
            case 1:
                OrderMainMenu.main(null);
                break;
            case 2:
            	System.out.println("Enter username");
                String loginID = sc.nextLine();
                System.out.println("Enter password");
                String password = sc.nextLine();
            	Staff loggedInStaff = Validate.validateStaff(staffList, loginID, password); // get staffList from backend?
            	if (loggedInStaff == null) {
            		System.out.println("Invalid login credentials!");
            	}
            	else loggedInStaff.staffMenu();
                break;
        }
    }
}