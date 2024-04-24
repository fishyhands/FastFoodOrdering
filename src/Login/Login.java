package Login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Database.Database;
import Exceptions.UnknownStaffRoleException;
import Order.OrderMainMenu;


public class Login {

    public static void main(String[] args){

        try {
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
            throw new InputMismatchException();
        }

        switch (userType) {
            case 1:
                OrderMainMenu.main(null);
                break;
            case 2:
                ArrayList<Staff.Staff> staffList = Database.readStaffList();
                System.out.println("Enter username");
                String loginID = sc.next().trim();

                System.out.println("Enter password");
                String password = sc.next().trim();


                Staff.Staff loggedInStaff = Validate.validateStaff(staffList, loginID, password);
                if (loggedInStaff == null) {
                    System.out.println("Invalid login credentials!");
                }
                else {
                    Staff.Staff loggedOutStaff = loggedInStaff.staffMenu();
                    staffList.remove(loggedInStaff);
                    staffList.add(loggedOutStaff);
                    Database.writeStaffList(staffList);
                }
            }

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
        }
        System.out.println();


    }
}