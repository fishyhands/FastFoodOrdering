package staff;

import java.util.*;

import exceptions.UnknownStaffRoleException;

/**
 * Provides methods to filter and display staff information based on various criteria.
 */
public class DisplayFilters {

    /**
     * Filters staff members by age and displays them in ascending or descending order.
     *
     * @param staffArr The list of staff members to be filtered.
     */
    private static void filterAge(ArrayList<Staff> staffArr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter:\n\t1. to display youngest first\n\t2. to display oldest first");
        int arrLength = staffArr.size();
        String choice = sc.next();
        switch (choice) {
            case "1": //youngest first
                displayAscending(staffArr);
                break;
            case "2": //oldest first
                displayDescending(staffArr);
                break;
            default:
                System.out.println("Error! Incorrect input");
        }
    }

    /**
     * Filters staff members by branch and displays them.
     *
     * @param branch   The branch name to filter by.
     * @param staffArr The list of staff members to be filtered.
     */
    private static void filterBranch(String branch, ArrayList<Staff> staffArr) {
        ArrayList<Staff> filteredArr = new ArrayList<>();
        boolean found = false;
        for (Staff o : staffArr) {
            if (o.getBranch().equals(branch)) {
                found = true;
                filteredArr.add(o);
            }
        }
        if (found) {
            displayFile(filteredArr);
        } else {
            System.out.println("No staff in this branch: " + branch);
        }
    }

    /**
     * Filters staff members by role and displays them.
     *
     * @param role     The role to filter by.
     * @param staffArr The list of staff members to be filtered.
     */
    private static void filterRole(String role, ArrayList<Staff> staffArr) {
        if (role.equals("S") || role.equals("M") || role.equals("A")) {
            ArrayList<Staff> filteredArr = new ArrayList<>();
            for (Staff o : staffArr) {
                if (o.getRole().equals(role)) {
                    filteredArr.add(o);
                }
            }
            displayFile(filteredArr);
        } else {
            System.out.println("Role does not exist");
        }
    }

    /**
     * Filters staff members by gender and displays them.
     *
     * @param gender   The gender to filter by.
     * @param staffArr The list of staff members to be filtered.
     */
    private static void filterGender(String gender, ArrayList<Staff> staffArr) {
        if (gender.equals("M") || gender.equals("F")) {
            ArrayList<Staff> filteredArr = new ArrayList<>();
            for (Staff o : staffArr) {
                if (o.getGender().equals(gender)) {
                    filteredArr.add(o);
                }
            }
            displayFile(filteredArr);
        } else {
            System.out.println("Incorrect gender");
        }
    }

    /**
     * Displays the filtered staff information based on user input.
     *
     * @param staffArr The list of staff members to be filtered and displayed.
     */
    public static void run(ArrayList<Staff> staffArr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter: \n1 to filter by age\n2 to filter by branch\n3 to filter by role\n4 to filter by gender ");
        String choice = sc.next();
        switch (choice) {
            case "1":
                filterAge(staffArr);
                break;
            case "2":
                System.out.println("Enter branch name: ");
                String branch = sc.next();
                filterBranch(branch, staffArr);
                break;
            case "3":
                System.out.println("Enter role: ");
                String role = sc.next();
                filterRole(role, staffArr);
                break;
            case "4":
                System.out.println("Enter gender: ");
                String gender = sc.next();
                filterGender(gender, staffArr);
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    /**
     * Comparator class to compare staff members by age.
     */
    static class AgeComparator implements Comparator<Staff> {
        @Override
        public int compare(Staff staff1, Staff staff2) {
            return Integer.compare(staff1.getAge(), staff2.getAge());
        }
    }

    /**
     * Displays the list of staff members in ascending order of age.
     *
     * @param staffList The list of staff members to be displayed.
     */
    private static void displayAscending(ArrayList<Staff> staffList) {
        staffList.sort(new AgeComparator());
        displayFile(staffList);
    }

    /**
     * Displays the list of staff members in descending order of age.
     *
     * @param staffList The list of staff members to be displayed.
     */
    private static void displayDescending(ArrayList<Staff> staffList) {
        staffList.sort(new AgeComparator().reversed());
        displayFile(staffList);
    }

    /**
     * Displays the staff information.
     *
     * @param staffArr The list of staff members to be displayed.
     */
    private static void displayFile(ArrayList<Staff> staffArr) {
        System.out.println("Staff Name\t\t| Login ID\t| Branch\t| Role\t| Gender\t| Age");
        System.out.println("-------------------------");
        for (Staff s : staffArr) {
            System.out.printf("%-15s | %-10s | %-10s | %-5s | %-6s | %-5s |%n", s.getStaffName(), s.getLoginID(), s.getBranch(), s.getRole(), s.getGender(), s.getAge());
        }
    }
}
