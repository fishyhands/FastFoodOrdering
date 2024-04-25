package database;

import java.io.*;
import java.net.URL;
import java.time.LocalTime;
import java.util.*;

import branch.Branch;
import exceptions.UnknownStaffRoleException;
import menu.Menu;
import order.Order;
import order.Order_Status;
import payment.PaymentMethod;
import staff.Admin;
import staff.BranchStaff;
import staff.Manager;
import staff.Staff;

/**
 * The Database class provides methods for reading and writing data to/from files.
 * It handles operations related to branches, staff, menus, orders, and payment methods.
 */
public class Database {
    private static final String BRANCHLIST = "branch_list.txt";
    private static final String STAFFLIST = "staff_list.txt";
    private static final String MENULIST = "menu_list.txt";
    private static final String ORDERLIST = "order_list.txt";
    private static final String PAYMENTLIST = "payment_methods.txt";
    private static final String SEPARATOR = ",";
    /**
     * Reads the list of branches from the database file.
     *
     * @return An ArrayList containing Branch objects read from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */

    public static ArrayList<Branch> readBranchList() throws IOException {
        ArrayList<String> stringArray = (ArrayList) read(filePath(BRANCHLIST)); // file data to string
        ArrayList<Branch> alr = new ArrayList<>(); // store Branch objects

        for (String o : stringArray) {
            StringTokenizer star = new StringTokenizer(o, SEPARATOR);
            String branchName = star.nextToken().trim();
            String branchLocation  = star.nextToken().trim();
            int staffQuota = Integer.parseInt(star.nextToken().trim());
            boolean openOrClose = Boolean.parseBoolean(star.nextToken().trim());
            Branch branch = new Branch(branchName, branchLocation, staffQuota, openOrClose);
            alr.add(branch);
        }
        return alr;
    }
    /**
     * Writes the list of branches to the database file.
     *
     * @param branches The list of Branch objects to write to the file.
     * @throws IOException if an I/O error occurs while writing the file.
     */

    public static void writeBranchList(ArrayList<Branch> branches) throws IOException {
        ArrayList<String> alw = new ArrayList<>();

        for (Branch o : branches) {
            StringBuilder st = new StringBuilder();
            st.append(o.getBranchName().trim());
            st.append(SEPARATOR);
            st.append(o.getBranchLocation());
            st.append(SEPARATOR);
            st.append(o.getStaffQuota());
            st.append(SEPARATOR);
            st.append(o.getOpenOrClose());
            alw.add(st.toString());
        }
        write(filePath(BRANCHLIST), alw);
    }
    /**
     * Reads the list of staff members from the database file.
     *
     * @return An ArrayList containing Staff objects read from the file.
     * @throws IOException                if an I/O error occurs while reading the file.
     * @throws UnknownStaffRoleException if an unknown staff role is encountered while reading.
     */

    public static ArrayList<Staff> readStaffList() throws IOException, UnknownStaffRoleException {
        ArrayList<String> stringArray = (ArrayList) read(filePath(STAFFLIST));
        ArrayList<Staff> alr = new ArrayList<>();
        for (String o : stringArray) {
            StringTokenizer star = new StringTokenizer(o, SEPARATOR);
            String name = star.nextToken().trim();
            String loginID = star.nextToken().trim();
            String password = star.nextToken().trim();
            String role = star.nextToken().trim();
            String gender = star.nextToken().trim();
            int age = Integer.parseInt(star.nextToken().trim());
            String branch = star.nextToken().trim();
            if (role.equals("S")) {alr.add(new BranchStaff(name, loginID, password, role, gender, age, branch));}
            else if (role.equals("M")) {alr.add(new Manager(name, loginID, password, role, gender, age, branch));}
            else if (role.equals("A")) {alr.add(new Admin(name, loginID, password, role, gender, age, branch));}
            else {throw new UnknownStaffRoleException("Unknown Staff Role");}
        }
        return alr;
    }

    /**
     * Writes the list of staff members to the database file.
     *
     * @param staffList The list of Staff objects to write to the file.
     * @throws IOException if an I/O error occurs while writing the file.
     */


    public static void writeStaffList(ArrayList<Staff> staffList) throws IOException {
        ArrayList<String> alw = new ArrayList<>();

        for (Staff o : staffList) {
            StringBuilder st = new StringBuilder();
            st.append(o.getStaffName().trim());
            st.append(SEPARATOR);
            st.append(o.getLoginID().trim());
            st.append(SEPARATOR);
            st.append(o.getPassword().trim());
            st.append(SEPARATOR);
            st.append(o.getRole().trim());
            st.append(SEPARATOR);
            st.append(o.getGender().trim());
            st.append(SEPARATOR);
            st.append(o.getAge());
            st.append(SEPARATOR);
            st.append(o.getBranch().trim());
            alw.add(st.toString());
        }
        write(filePath(STAFFLIST), alw);
    }
    /**
     * Reads the list of menu items from the database file.
     *
     * @return An ArrayList containing Menu objects read from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public static ArrayList<Menu> readMenuList() throws IOException {
        ArrayList<String> stringArray = (ArrayList) read(filePath(MENULIST));
        ArrayList<Menu> alr = new ArrayList<>();
        for (String s : stringArray) {
            StringTokenizer star = new StringTokenizer((String) s, SEPARATOR);
            String name = star.nextToken().trim();
            Float price = Float.valueOf(star.nextToken().trim());
            String branch = star.nextToken().trim();
            String category = star.nextToken().trim();
            boolean availability = Boolean.parseBoolean(star.nextToken().trim());
            Menu menuItem = new Menu(name, price, branch, category, availability);
            alr.add(menuItem);
        }
        return alr;
    }
    /**
     * Writes the list of menu items to the database file.
     *
     * @param menuList The list of Menu objects to write to the file.
     * @throws IOException if an I/O error occurs while writing the file.
     */
    public static void writeMenuList(ArrayList<Menu> menuList) throws IOException {
        ArrayList<String > alw = new ArrayList<>();

        for (Menu o : menuList) {
            StringBuilder st = new StringBuilder();
            st.append(o.getName().trim());
            st.append(SEPARATOR);
            st.append(o.getPrice());
            st.append(SEPARATOR);
            st.append(o.getBranch().trim());
            st.append(SEPARATOR);
            st.append(o.getCategory().trim());
            st.append(SEPARATOR);
            st.append(o.isAvailable());
            alw.add(st.toString());
        }
        write(filePath(MENULIST), alw);
    }
    /**
     * Reads the list of orders from the database file.
     *
     * @return An ArrayList containing Order objects read from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public static ArrayList<Order> readOrderList() throws IOException {
        ArrayList<Order> orderList = new ArrayList<>();
        ArrayList<String> stringArray = (ArrayList) read(filePath(ORDERLIST));
        for (String o : stringArray) {
            StringTokenizer star = new StringTokenizer(o, SEPARATOR);

            int orderID = Integer.parseInt(star.nextToken().trim());
            String branch = star.nextToken().trim();
            boolean paid = Boolean.parseBoolean(star.nextToken().trim());
            Order_Status order_status = Order_Status.valueOf(star.nextToken().trim());
            boolean takeaway = Boolean.parseBoolean(star.nextToken().trim());
            HashMap<String, Integer> cart = new HashMap<>();
            // Extract cart items from data
            String[] cartData = star.nextToken().split(":");
            for (String item : cartData) {
                String[] itemData = item.split("=");
                cart.put(itemData[0], Integer.parseInt(itemData[1]));
            }
            float totalSum = Float.parseFloat(star.nextToken().trim());
            LocalTime time = LocalTime.parse(star.nextToken());
            Order order = new Order(orderID, branch, paid, order_status, takeaway, cart, totalSum,time);
            orderList.add(order);
        }
        return orderList;
    }
    /**
     * Writes the list of orders to the database file.
     *
     * @param orderList The list of Order objects to write to the file.
     * @throws IOException if an I/O error occurs while writing the file.
     */

    public static void writeOrderList(ArrayList<Order> orderList) throws IOException {
        ArrayList<String > alw = new ArrayList<>();

        for (Order o : orderList) {
            StringBuilder st = new StringBuilder();
            st.append(o.getOrderID());
            st.append(SEPARATOR);
            st.append(o.getBranch().trim());
            st.append(SEPARATOR);
            st.append(o.isPaid());
            st.append(SEPARATOR);
            st.append(o.getStatus());
            st.append(SEPARATOR);
            st.append(o.isTakeaway());
            st.append(SEPARATOR);
            // add Hashmap cart
            HashMap<String,Integer> cart = o.getCart();
            for (Map.Entry<String,Integer> entry : cart.entrySet()){
                st.append(entry.getKey()).append("=").append(entry.getValue()).append(":");
            }
            if(!cart.isEmpty()){
                st.deleteCharAt(st.length() - 1);
            }
            st.append(SEPARATOR);
            st.append(o.getTotalSum());
            st.append(SEPARATOR);
            st.append(o.getTime());
            alw.add(st.toString());
        }
        write(filePath(ORDERLIST), alw);
    }
    /**
     * Reads the list of payment methods from the database file.
     *
     * @return An ArrayList containing PaymentMethod objects read from the file.
     * @throws IOException            if an I/O error occurs while reading the file.
     * @throws ClassNotFoundException if the class of a serialized object cannot be found.
     */


    public static ArrayList readPaymentMethods() throws IOException, ClassNotFoundException{
        ArrayList paymentMethods = null;
        FileInputStream fileIn = new FileInputStream(filePath(PAYMENTLIST));
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        paymentMethods = (ArrayList) objectIn.readObject();
        objectIn.close();
        return paymentMethods;
    }
    /**
     * Writes the list of payment methods to the database file.
     *
     * @param paymentMethods The list of PaymentMethod objects to write to the file.
     * @throws IOException if an I/O error occurs while writing the file.
     */
    public static void writePaymentMethods(ArrayList paymentMethods) throws IOException{
        FileOutputStream fileOut = new FileOutputStream(filePath(PAYMENTLIST));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(paymentMethods);
        objectOut.close();
    }

// Private utility methods for reading/writing files and constructing file paths

    /**
     * Writes data to a file.
     *
     * @param fileName The name of the file to write to.
     * @param data     The data to write to the file.
     * @throws IOException if an I/O error occurs while writing the file.
     */
    private static void write(String fileName, ArrayList<String> data) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));
        try {
            for (String datum : data) {
                out.println(datum);
            }
        } finally {
            out.close();
        }
    }
    /**
     * Reads data from a file.
     *
     * @param fileName The name of the file to read from.
     * @return An ArrayList containing the data read from the file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    private static ArrayList<String> read(String fileName) throws IOException {
        ArrayList<String> data = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }
    /**
     * Constructs the file path for a given file name.
     *
     * @param fileName The name of the file.
     * @return The file path.
     */

    private static String filePath(String fileName){
        URL url = Database.class.getResource(fileName);
        assert url != null;
        return url.getPath();
    }
}