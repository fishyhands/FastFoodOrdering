package branch;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;

public class BranchList {
	private static ArrayList<Branch> branchList;
	
	public BranchList() throws IOException {
		branchList = Database.readBranchList();
	}
	
	public static ArrayList<Branch> getBranchList() {
		return branchList;
	}
	
    public static void displayBranches(){        
        for (int i = 0; i < branchList.size(); i++) {
            if (branchList.get(i).getOpenOrClose()){
                System.out.println((i + 1) + ". " + branchList.get(i).getBranchName());
            }
        }
    }
    
    public static void writeBranchList() throws IOException {
    	Database.writeBranchList(branchList);
    }
}
