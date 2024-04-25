package branch;

public class Branch {
    /**
    * The branch name.
    */
    private String branchName;
    /**
    * The location of the branch.
    */
    private String branchLocation;
    /**
    * The staff quota of the branch.
    */
    private int staffQuota;
    /**
    * Whether the branch is open or closed.
    */
    private boolean openOrClose;

    /**
    * Creates a new Branch with the given branchName, branchLocation, staffQuota and whether it is open or closed.
    * @param branchName This Branch's name.
    * @param branchLocation This Branch's location.
    * @param staffQuota This Branch's staff quota.
    * @param openOrClose This Branch is open or closed.
    */
    //constructor
    public Branch(String branchName, String branchLocation, int staffQuota,boolean openOrClose) {
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.staffQuota = staffQuota;
        this.openOrClose = openOrClose;
    }


    //get
    /**
    * Gets the Branch's name.
    * @return this Branch's name.
    */
    public String getBranchName() {return branchName;}
    /**
    * Gets the Branch's location.
    * @return this Branch's location.
    */
    public String getBranchLocation(){return branchLocation;}
    /**
    * Gets the Branch's staff quota.
    * @return this Branch's staff quota.
    */
    public int getStaffQuota(){return staffQuota;}
    /**
    * Gets whether the Branch's is open or closed.
    * @return whether the Branch's is open or closed.
    */
    public boolean getOpenOrClose(){return openOrClose;}

    //set
    /**
    * Changes the name of this Branch.
    * @param branchName This Branch's new name.
    */
    public void setBranchName(String branchName) {this.branchName = branchName;}
    /**
    * Changes the location of this Branch.
    * @param branchName This Branch's new location.
    */
    public void setBranchLocation(String branchLocation) {this.branchLocation = branchLocation;}
    /**
    * Changes the staff quota of this Branch.
    * @param branchName This Branch's new staff quota.
    */
    public void setStaffQuota(int staffQuota) {this.staffQuota = staffQuota;}
    /**
    * Changes whether the branch is open or closed.
    * @param openOrClose This Branch is open or closed.
    */
    public void setOpenOrClose(boolean openOrClose) {this.openOrClose = openOrClose;}
}
