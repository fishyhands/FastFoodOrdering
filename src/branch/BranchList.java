package branch;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;

/**
 * The BranchList class represents a list of branches.
 * It provides methods to interact with the list of branches, such as retrieving the list,
 * displaying open branches, and writing the list to a database.
 */
public class BranchList {
    /**
     * The list of branches.
     */
    private static ArrayList<Branch> branchList;

    /**
     * Constructs a new BranchList object by reading the list of branches from a database.
     *
     * @throws IOException if an I/O error occurs while reading the branch list from the database.
     */
    public BranchList() throws IOException {
        branchList = Database.readBranchList();
    }

    /**
     * Retrieves the list of branches.
     *
     * @return The list of branches.
     */
    public static ArrayList<Branch> getBranchList() {
        return branchList;
    }

    /**
     * Displays the names of open branches.
     */
    public static void displayBranches(){
        for (int i = 0; i < branchList.size(); i++) {
            if (branchList.get(i).getOpenOrClose()){
                System.out.println((i + 1) + ". " + branchList.get(i).getBranchName());
            }
        }
    }

    /**
     * Writes the list of branches to a database.
     *
     * @throws IOException if an I/O error occurs while writing the branch list to the database.
     */
    public static void writeBranchList() throws IOException {
        Database.writeBranchList(branchList);
    }
}
