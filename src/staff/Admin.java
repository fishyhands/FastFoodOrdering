package staff;

import branch.Branch;
import exceptions.UnknownStaffRoleException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
/**
 * The Admin class represents an admin staff member.
 * It extends the Staff class and provides methods to manage staff, branches, and assign managers.
 */

public class Admin extends Staff {
    private String name;
    private String loginID;
    private String password;
    private String adminRole;
    private String gender;
    private int age;
    private String branch;

    /**
     * Constructs a new Admin object with the specified attributes.
     *
     * @param name       The name of the admin.
     * @param loginID    The login ID of the admin.
     * @param password   The password of the admin.
     * @param adminRole  The role of the admin.
     * @param gender     The gender of the admin.
     * @param age        The age of the admin.
     * @param branch     The branch associated with the admin.
     */

    public Admin(String name, String loginID, String password, String adminRole, String gender, int age, String branch){
        super(name, loginID, password, adminRole, gender, age, branch);

    }

    /**
     * Sets the password for the admin.
     *
     * @param password The new password to set.
     */
    public void setPassword(String password) {this.password = password;}

    /**
     * Adds a new staff member to the system.
     *
     * @param name      The name of the staff member.
     * @param id        The ID of the staff member.
     * @param password  The password of the staff member.
     * @param role      The role of the staff member.
     * @param gender    The gender of the staff member.
     * @param age       The age of the staff member.
     * @param branch    The branch associated with the staff member.
     * @param staffList The list of staff members.
     */
    public void addStaff(String name, String id, String password, String role, String gender, int age, String branch, ArrayList<Staff> staffList){
        BranchStaff newStaff = new BranchStaff(name,id,password,role,gender,age,branch);
        staffList.add(newStaff);
        System.out.println("New Staff " + name + " added");
    }

    /**
     * Removes a staff member from the system.
     *
     * @param staffName The name of the staff member to remove.
     * @param staffList The list of staff members.
     */

    public void removeStaff(String staffName, ArrayList<Staff> staffList){
        for (Staff o: staffList){
            if (o.getStaffName().equals(staffName)){
                staffList.remove(o);
                System.out.println(o.getStaffName() + " removed");
                return;
            }
        }
        System.out.println("Error:\nStaff not found");
    }

    /**
     * Edits the details of a staff member.
     *
     * @param choice    The choice of attribute to edit.
     * @param staffName The name of the staff member to edit.
     * @param staffList The list of staff members.
     */

    public void editStaff(int choice, String staffName, ArrayList<Staff> staffList){
        for (Staff o:staffList){
            if (o.getStaffName().equals(staffName)){
            	Scanner sc = new Scanner(System.in);
                switch (choice){
                    case 1:
                        System.out.println("Editing Staff Name");
                        System.out.print("Please write new name: ");
                        String newName = sc.nextLine();
                        o.setStaffName(newName);
                        break;
                    case 2:
                        System.out.println("Editing Staff Login ID");
                        System.out.print("Please write new login id: ");
                        String newID = sc.nextLine();
                        o.setLoginID(newID);
                        break;
                    case 3:
                        System.out.println("Editing Staff Role");
                        System.out.print("Please write new role: ");
                        String newRole = sc.next();
                        if (Objects.equals(newRole, "M") || (Objects.equals(newRole,"S"))){
                            o.setRole(newRole);
                        }else{
                            System.out.println("Role does not exist");
                        }
                        break;
                    case 4:
                        System.out.println("Editing Staff Gender");
                        System.out.print("Please write new gender: ");
                        String newGender = sc.next();
                        o.setGender(newGender);
                        break;
                    case 5:
                        System.out.println("Editing Staff Age");
                        System.out.print("Please write new age: ");
                        int newAge = sc.nextInt();
                        o.setAge(newAge);
                        break;
                    case 6:
                        System.out.println("Editing Staff Branch");
                        System.out.print("Please write new branch: ");
                        String newBranch = sc.nextLine();
                        o.setBranch(newBranch);
                        break;
                    default:
                        System.out.println("Wrong choice");
                        break;
                }
                return;
            }
        }
        System.out.println("Error:\nStaff not found");
    }

    /**
     * Changes the status of a branch (open/close).
     *
     * @param branchName The name of the branch to change status.
     * @param branchList The list of branches.
     */

    public void changeBranchStatus(String branchName, ArrayList<Branch> branchList) {
        for (Branch o : branchList) {
            if (o.getBranchName().equals(branchName)) {
                if (o.getOpenOrClose()) {
                    o.setOpenOrClose(false);
                    System.out.println(o.getBranchName() + " is now CLOSED");
                } else {
                    o.setOpenOrClose(true);
                    System.out.println(o.getBranchName() + " is now OPEN");
                }
                return;
            }
        }
        System.out.println("Error:\nBranch does not exist");
    }

    /**
     * Assigns managers to branches based on staff count.
     *
     * @param branchList The list of branches.
     * @param staffList  The list of staff members.
     */

    public void assignManager(ArrayList<Branch> branchList, ArrayList<Staff> staffList){
        for(Branch branch: branchList) {
            int staffCount = getStaffCount(branch, staffList);
            int requiredManagers = calculateRequiredManagers(staffCount) - getManagerCount(branch,staffList);
            if (requiredManagers > 0) {
                System.out.println("Branch: " + branch.getBranchName() + " has insufficient managers.");
                System.out.println("It requires " + requiredManagers + " manager(s).");
            }
        }

    }

    /**
     * Opens a new branch and adds it to the branch list.
     *
     * @param branchList    The list of branches.
     * @param branchName    The name of the new branch.
     * @param branchLocation The location of the new branch.
     * @param staffQuota    The staff quota for the new branch.
     */

    public void openNewBranch(ArrayList<Branch> branchList, String branchName, String branchLocation, int staffQuota) {
        branchList.add(new Branch(branchName, branchLocation, staffQuota, true));
    }

    /**
     * Calculates the required number of managers based on the staff count.
     *
     * @param staffCount The number of staff members.
     * @return The required number of managers.
     */

    // helper functions to assign manager
    private int calculateRequiredManagers(int staffCount){
        if(staffCount >= 1 && staffCount <= 4){
            return 1;
        }
        else if(staffCount >= 5 && staffCount <= 8){
            return 2;
        }
        else if(staffCount >= 9 && staffCount <= 15){
            return 3;
        }
        else{
            return 0;
        }
    }

    /**
     * Gets the count of staff members associated with a branch.
     *
     * @param branch    The branch to count staff for.
     * @param staffList The list of staff members.
     * @return The count of staff members associated with the branch.
     */

    private int getStaffCount(Branch branch, ArrayList<Staff> staffList){
        int count = 0;
        for(Staff staff: staffList){
            if(staff.getBranch().equals(branch.getBranchName())){
                count++;
            }
        }
        return count;
    }

    /**
     * Gets the count of managers associated with a branch.
     *
     * @param branch    The branch to count managers for.
     * @param staffList The list of staff members.
     * @return The count of managers associated with the branch.
     */

    private int getManagerCount(Branch branch, ArrayList<Staff> staffList){
        int count = 0;
        for(Staff staff: staffList){
            if(staff.getBranch().equals(branch.getBranchName()) && staff.getRole().equals("M")){
                count++;
            }
        }
        return count;
    }

    /**
     * Displays the admin menu for admin tasks.
     *
     * @throws IOException               If an I/O error occurs.
     * @throws UnknownStaffRoleException If an unknown staff role is encountered.
     * @throws ClassNotFoundException    If the class of a serialized object cannot be found.
     */

    public void staffMenu() throws IOException, UnknownStaffRoleException, ClassNotFoundException {
        AdminMainMenu.mainMenu(this);
    }
}