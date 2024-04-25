package staff;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;
import exceptions.UnknownStaffRoleException;

/**
 * Represents a list of staff members in the system.
 */
public class StaffList {
	private static ArrayList<Staff> staffList;

	/**
	 * Initializes the staff list by reading from the database.
	 *
	 * @throws IOException               If an I/O error occurs.
	 * @throws UnknownStaffRoleException If the staff role is unknown.
	 */
	public StaffList() throws IOException, UnknownStaffRoleException {
		staffList = Database.readStaffList();
	}

	/**
	 * Retrieves the list of staff members.
	 *
	 * @return The list of staff members.
	 */
	public static ArrayList<Staff> getStaffList() {
		return staffList;
	}

	/**
	 * Writes the staff list to the database.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	public static void writeStaffList() throws IOException {
		Database.writeStaffList(staffList);
	}
}
