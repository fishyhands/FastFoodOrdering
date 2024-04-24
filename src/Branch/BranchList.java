package Branch;

import java.util.ArrayList;

public class BranchList {

    public static void displayBranches(ArrayList<Branch> branches){        
        for (int i = 0; i < branches.size(); i++) {
            System.out.println((i + 1) + ". " + branches.get(i).getBranchName());
        }
    }
}
