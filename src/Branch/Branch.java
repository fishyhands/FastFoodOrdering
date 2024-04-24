package Branch;

public class Branch {
    private String branchName;
    private String branchLocation;
    private int staffQuota;
    private boolean openOrClose;

    //constructor
    public Branch(String branchName, String branchLocation, int staffQuota,boolean openOrClose) {
        this.branchName = branchName;
        this.branchLocation = branchLocation;
        this.staffQuota = staffQuota;
        this.openOrClose = openOrClose;
    }

    //get
    public String getBranchName() {return branchName;}

    public String getBranchLocation(){return branchLocation;}

    public int getStaffQuota(){return staffQuota;}

    public boolean getOpenOrClose(){return openOrClose;}

    //set
    public void setBranchName(String branchName) {this.branchName = branchName;}

    public void setBranchLocation(String branchLocation) {this.branchLocation = branchLocation;}

    public void setStaffQuota(int staffQuota) {this.staffQuota = staffQuota;}

    public void setOpenOrClose(boolean openOrClose) {this.openOrClose = openOrClose;}
}