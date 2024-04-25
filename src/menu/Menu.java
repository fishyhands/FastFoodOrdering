package menu;

public class Menu {
    private String name;
    private float price;
    private String branch;
    private String category;
    private boolean availability;

    public Menu(String n, float p, String branch, String category,boolean availability){
        this.name = n;
        this.price = p;
        this.branch = branch;
        this.category = category;
        this.availability = availability;
    }

    //get
    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getBranch(){ return branch;}

    public String getCategory(){ return category;}

    public boolean isAvailable() {
        return availability;
    }

    //set
    public void setPrice(float p) {this.price = p;}

    public void setName(String n) {this.name = n;}

    public void setBranch(String branch){this.branch = branch;}

    public void setAvailability(boolean availability) {this.availability = availability;}



}
