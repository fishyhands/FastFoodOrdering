package Login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Database.Database;
import Exceptions.UnknownStaffRoleException;
import Order.OrderMainMenu;


public class Login {

    public static void main(String[] args){
        try{
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
            	ArrayList<Staff.Staff> staffList = Database.readStaffList();
                System.out.println("Enter username");
                System.out.println("Press Enter to continue...");
                String loginID = sc.next().trim();

                // Wait for the user to press Enter before prompting for the password

                sc.nextLine();

                System.out.println("Enter password");
                String password = sc.next().trim();


            	Staff.Staff loggedInStaff = Validate.validateStaff(staffList, loginID, password); // get staffList from backend?
            	if (loggedInStaff == null) {
            		System.out.println("Invalid login credentials!");
            	}
            	else{
                    staffList.remove(loggedInStaff);
                    switch (loggedInStaff.getRole()) {
                        case "S" -> {
                            Staff.BranchStaff loggedInBStaff = (Staff.BranchStaff) loggedInStaff;
                            Staff.BranchStaff staff = Staff.StaffMainMenu.mainMenu(loggedInBStaff);
                            staffList.add(staff);
                            Database.writeStaffList(staffList);
                        }
                        case "M" -> {
                            Staff.Manager loggedInMan = (Staff.Manager) loggedInStaff;
                            Staff.Manager manager = Staff.ManagerMainMenu.mainMenu(loggedInMan);
                            staffList.add(manager);
                            Database.writeStaffList(staffList);
                        }
                        case "A" -> {
                            Staff.Admin loggedInAd = (Staff.Admin) loggedInStaff;
                            Staff.Admin admin = Staff.AdminMainMenu.mainMenu(loggedInAd);
                            staffList.add(admin);
                            Database.writeStaffList(staffList);
                        }
                        default -> throw new UnknownStaffRoleException("Unknown Staff Role");
                    }
                    }
                }


        } catch (UnknownStaffRoleException ex) {
            System.out.println("UnknownStaffRoleException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        }

    }
}