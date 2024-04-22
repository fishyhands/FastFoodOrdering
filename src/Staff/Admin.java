package Staff;

import Branch.Branch;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Admin {
    private String name;
    private String loginID;
    private String password;
    private String adminRole;
    private String gender;
    private int age;
    private String branch;

    public Admin(String name, String loginID, String password, String adminRole, String gender, int age, String branch){
        this.name = name;
        this.loginID = loginID;
        this.password = password;
        this.adminRole = adminRole;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
    }

    public void setPassword(String password) {this.password = password;}


    public void addStaff(String name, String id, String password, String role, String gender, int age, String branch, ArrayList<Staff> staffList){
        Staff newStaff = new Staff(name,id,password,role,gender,age,branch);
        staffList.add(newStaff);
        System.out.println("New Staff " + name + " added");
    }

    public void removeStaff(Staff staff, ArrayList<Staff> staffList){
        for (Staff o: staffList){
            if (o.equals(staff)){
                staffList.remove(staff);
                System.out.println(staff.getStaffName() + " removed");
                break;
            }
            else{System.out.println("Error:\nStaff not found");}
        }
    }

    public void editStaff(int choice, Staff staff, ArrayList<Staff> staffList){
        Scanner sc = new Scanner(System.in);
        for (Staff o:staffList){
            if (o.equals(staff)){
                switch (choice){
                    case 1:
                        System.out.println("Editing Staff Name");
                        System.out.print("Please write new name: ");
                        String newName = sc.nextLine();
                        o.setStaffName(newName);
                    case 2:
                        System.out.println("Editing Staff Login ID");
                        System.out.print("Please write new login id: ");
                        String newID = sc.nextLine();
                        o.setLoginID(newID);
                    case 3:
                        System.out.println("Editing Staff Role");
                        System.out.print("Please write new role: ");
                        String newRole = sc.next();
                        o.setRole(newRole);
                    case 4:
                        System.out.println("Editing Staff Gender");
                        System.out.print("Please write new gender: ");
                        String newGender = sc.next();
                        o.setGender(newGender);
                    case 5:
                        System.out.println("Editing Staff Age");
                        System.out.print("Please write new age: ");
                        int newAge = sc.nextInt();
                        o.setAge(newAge);
                    case 6:
                        System.out.println("Editing Staff Branch");
                        System.out.print("Please write new branch: ");
                        String newBranch = sc.nextLine();
                        o.setBranch(newBranch);
                    default:
                        System.out.println("Wrong choice");
                }
                break;
            }
            else{System.out.println("Error:\nStaff not found");}
        }
    }

    public void changeBranchStatus(Branch branch, ArrayList<Branch> branchList) {
        for (Branch o : branchList) {
            if (Objects.equals(o, branch)) {
                if (o.getOpenOrClose()) {
                    o.setOpenOrClose(false);
                    System.out.println(o.getBranchName() + " is now CLOSED");
                } else {
                    o.setOpenOrClose(true);
                    System.out.println(o.getBranchName() + " is now OPEN");
                }
                break;
            } else {
                System.out.println("Error:\nBranch does not exist");
            }
        }
    }

    public void assignManager(ArrayList<Branch> branchList, ArrayList<Staff> staffList){
        for(Branch branch: branchList) {
            int staffCount = getStaffCount(branch, staffList);
            int requiredManagers = calculateRequiredManagers(staffCount);
            if (requiredManagers > 0) {
                System.out.println("Branch: " + branch.getBranchName() + "has insufficient managers.");
                System.out.println("It requires" + requiredManagers + "manager(s).");
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

  //menu
    public void staffMenu() throws IOException {
    	AdminMainMenu.mainMenu(this);
    }
}
