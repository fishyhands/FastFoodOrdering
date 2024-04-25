package staff;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;
import exceptions.UnknownStaffRoleException;

public abstract class Staff {

    private String staffName;
    private String loginID;
    private String staffPassword;
    private String role;
    private String gender;
    private int age;
    private String branch;

    // Constructor
    public Staff(String staffName, String loginID, String staffPassword, String role, String gender, int age, String branch) {
        this.staffName = staffName;
        this.loginID = loginID;
        this.staffPassword = staffPassword;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
    }


    //get
    public String getStaffName() {return this.staffName;}
    public String getLoginID() {return this.loginID;}
    public String getPassword() {return this.staffPassword;}
    public String getRole(){return this.role;}
    public String getGender(){return this.gender;}
    public int getAge(){return this.age;}
    public String getBranch(){return this.branch;}


    //set
    public void setStaffName(String staffName) {this.staffName = staffName;}
    public void setLoginID(String loginID) {this.loginID = loginID;}
    public void setPassword(String newPassword) throws IOException, UnknownStaffRoleException {
    	ArrayList<Staff> staffList = StaffList.getStaffList();
    	for (Staff s : staffList) {
            if (this.getLoginID().equals(s.getLoginID())) {
            	s.staffPassword = newPassword;
                break;
            }
        }
    }
    public void setRole(String role) {this.role = role;}
    public void setGender(String gender) {this.gender = gender;}
    public void setAge(int age) {this.age = age;}
    public void setBranch(String branch) {this.branch = branch;}


    public abstract void staffMenu() throws IOException, UnknownStaffRoleException, ClassNotFoundException;
}