package staff;

import database.Database;
import exceptions.UnknownStaffRoleException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a staff member in the system.
 */
public abstract class Staff {

    private String staffName;
    private String loginID;
    private String staffPassword;
    private String role;
    private String gender;
    private int age;
    private String branch;

    /**
     * Constructor for creating a Staff object.
     *
     * @param staffName     The name of the staff member.
     * @param loginID       The login ID of the staff member.
     * @param staffPassword The password of the staff member.
     * @param role          The role of the staff member.
     * @param gender        The gender of the staff member.
     * @param age           The age of the staff member.
     * @param branch        The branch where the staff member works.
     */
    public Staff(String staffName, String loginID, String staffPassword, String role, String gender, int age, String branch) {
        this.staffName = staffName;
        this.loginID = loginID;
        this.staffPassword = staffPassword;
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.branch = branch;
    }

    /**
     * Retrieves the name of the staff member.
     *
     * @return The name of the staff member.
     */
    public String getStaffName() {
        return this.staffName;
    }

    /**
     * Retrieves the login ID of the staff member.
     *
     * @return The login ID of the staff member.
     */
    public String getLoginID() {
        return this.loginID;
    }

    /**
     * Retrieves the password of the staff member.
     *
     * @return The password of the staff member.
     */
    public String getPassword() {
        return this.staffPassword;
    }

    /**
     * Retrieves the role of the staff member.
     *
     * @return The role of the staff member.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Retrieves the gender of the staff member.
     *
     * @return The gender of the staff member.
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Retrieves the age of the staff member.
     *
     * @return The age of the staff member.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Retrieves the branch where the staff member works.
     *
     * @return The branch where the staff member works.
     */
    public String getBranch() {
        return this.branch;
    }

    /**
     * Sets the name of the staff member.
     *
     * @param staffName The new name of the staff member.
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /**
     * Sets the login ID of the staff member.
     *
     * @param loginID The new login ID of the staff member.
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    /**
     * Sets the password of the staff member.
     *
     * @param newPassword The new password of the staff member.
     * @throws IOException               If an I/O error occurs.
     * @throws UnknownStaffRoleException If the staff role is unknown.
     */
    public void setPassword(String newPassword) throws IOException, UnknownStaffRoleException {
        ArrayList<Staff> staffList = StaffList.getStaffList();
        for (Staff s : staffList) {
            if (this.getLoginID().equals(s.getLoginID())) {
                s.staffPassword = newPassword;
                break;
            }
        }
    }

    /**
     * Sets the role of the staff member.
     *
     * @param role The new role of the staff member.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Sets the gender of the staff member.
     *
     * @param gender The new gender of the staff member.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the age of the staff member.
     *
     * @param age The new age of the staff member.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the branch where the staff member works.
     *
     * @param branch The new branch where the staff member works.
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Abstract method representing the menu for staff members.
     *
     * @throws IOException               If an I/O error occurs.
     * @throws UnknownStaffRoleException If the staff role is unknown.
     * @throws ClassNotFoundException    If the class of a serialized object cannot be found.
     */
    public abstract void staffMenu() throws IOException, UnknownStaffRoleException, ClassNotFoundException;
}
