package staff;

import branch.Branch;
import exceptions.UnknownStaffRoleException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Admin extends Staff {
    private String name;
    private String loginID;
    private String password;
    private String adminRole;
    private String gender;
    private int age;
    private String branch;

    public Admin(String name, String loginID, String password, String adminRole, String gender, int age, String branch){
        super(name, loginID, password, adminRole, gender, age, branch);

    }

    public void setPassword(String password) {this.password = password;}


    public void addStaff(String name, String id, String password, String role, String gender, int age, String branch, ArrayList<Staff> staffList){
        BranchStaff newStaff = new BranchStaff(name,id,password,role,gender,age,branch);
        staffList.add(newStaff);
        System.out.println("New Staff " + name + " added");
    }

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

    private int getStaffCount(Branch branch, ArrayList<Staff> staffList){
        int count = 0;
        for(Staff staff: staffList){
            if(staff.getBranch().equals(branch.getBranchName())){
                count++;
            }
        }
        return count;
    }

    private int getManagerCount(Branch branch, ArrayList<Staff> staffList){
        int count = 0;
        for(Staff staff: staffList){
            if(staff.getBranch().equals(branch.getBranchName()) && staff.getRole().equals("M")){
                count++;
            }
        }
        return count;
    }


    public void openNewBranch(ArrayList<Branch> branchList, String branchName, String branchLocation, int staffQuota) {
        branchList.add(new Branch(branchName, branchLocation, staffQuota, true));
    }

    public void staffMenu() throws IOException, UnknownStaffRoleException, ClassNotFoundException {
        AdminMainMenu.mainMenu(this);
    }
}