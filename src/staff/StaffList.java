package staff;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;
import exceptions.UnknownStaffRoleException;

public class StaffList {
	private static ArrayList<Staff> staffList;
		
		public StaffList() throws IOException, UnknownStaffRoleException {
			staffList = Database.readStaffList();
		}
		
		public static ArrayList<Staff> getStaffList() {
			return staffList;
		}
		
	    public static void writeStaffList() throws IOException {
	    	Database.writeStaffList(staffList);
	    }
}
