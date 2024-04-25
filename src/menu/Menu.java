package menu;

/**
 * The Menu class represents a menu item.
 * It contains information such as name, price, branch, category, and availability.
 */
public class Menu {
    private String name;
    private float price;
    private String branch;
    private String category;
    private boolean availability;

    /**
     * Constructs a Menu object with the specified name, price, branch, category, and availability.
     *
     * @param name         The name of the menu item.
     * @param price        The price of the menu item.
     * @param branch       The branch associated with the menu item.
     * @param category     The category of the menu item.
     * @param availability The availability status of the menu item.
     */
    public Menu(String name, float price, String branch, String category, boolean availability) {
        this.name = name;
        this.price = price;
        this.branch = branch;
        this.category = category;
        this.availability = availability;
    }

    /**
     * Retrieves the price of the menu item.
     *
     * @return The price of the menu item.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Retrieves the name of the menu item.
     *
     * @return The name of the menu item.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the branch associated with the menu item.
     *
     * @return The branch associated with the menu item.
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Retrieves the category of the menu item.
     *
     * @return The category of the menu item.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Checks if the menu item is available.
     *
     * @return True if the menu item is available, otherwise false.
     */
    public boolean isAvailable() {
        return availability;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param price The price to set for the menu item.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Sets the name of the menu item.
     *
     * @param name The name to set for the menu item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the branch associated with the menu item.
     *
     * @param branch The branch to set for the menu item.
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Sets the availability status of the menu item.
     *
     * @param availability The availability status to set for the menu item.
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
