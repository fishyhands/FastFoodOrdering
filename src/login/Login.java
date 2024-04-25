package login;

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


public class Login {

    public static void main(String[] args) throws IOException{

    	try {
        	new StaffList();
        	new OrderList();
        	new MenuList();
        	new BranchList();
        	new PaymentMethodList();

	        // select customer or Staff
	        Scanner sc = new Scanner(System.in);
	        System.out.println("======= Login Options =======");
	        System.out.println("|1. Customer                |");
	        System.out.println("|2. Staff                   |");
	        System.out.println("=============================");
	        System.out.println("Please enter selection: ");
	        int userType = sc.nextInt();
	
	        while (userType > 2 || userType < 1) {
	            throw new InputMismatchException();
	        }
	
	        switch (userType) {
	            case 1:
	                OrderMainMenu.main(null);
	                break;
	            case 2:
	                System.out.println("Enter username");
	                String loginID = sc.next().trim();
	
	                System.out.println("Enter password");
	                String password = sc.next().trim();
		
	                Staff loggedInStaff = Validate.validateStaff(loginID, password);
	                if (loggedInStaff == null) {
	                    System.out.println("Invalid login credentials!");
	                }
	                else {
	                    loggedInStaff.staffMenu();               
	                }
	            }
	
	            sc.close();
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
	        	StaffList.writeStaffList();
	        	BranchList.writeBranchList();
	        	OrderList.writeOrderList();
	        	MenuList.writeMenuList();
	        	PaymentMethodList.writePaymentMethods();
	        }
	        System.out.println();
    	}
}