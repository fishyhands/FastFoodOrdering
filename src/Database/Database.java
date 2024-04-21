package Database;

import java.io.*;
import java.net.URL;
import java.util.*;

import Branch.Branch;
import Staff.Staff;
import Menu.Menu;
import Order.Order;
import Order.Order_Status;


public class Database {
    private static final String BRANCHLIST = "branch_list.txt";
    private static final String STAFFLIST = "staff_list.txt";
    private static final String MENULIST = "menu_list.txt";
    private static final String ORDERLIST = "order_list.txt";
    private static final String PAYMENTLIST = "payment_methods.txt";
    private static final String SEPARATOR = ",";

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

    public static ArrayList<Staff> readStaffList() throws IOException {
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
            Staff staff = new Staff(name, loginID, password, role, gender, age, branch);
            alr.add(staff);
        }
        return alr;
    }

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
            Order order = new Order(orderID, branch, paid, order_status, takeaway, cart, totalSum);
            orderList.add(order);
        }
        return orderList;
    }

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
            alw.add(st.toString());
        }
        write(filePath(ORDERLIST), alw);
    }


    public static void readPaymentMethods() throws IOException, ClassNotFoundException{
        ArrayList paymentMethods = null;
        FileInputStream fileIn = new FileInputStream(filePath(PAYMENTLIST));
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        paymentMethods = (ArrayList) objectIn.readObject();
        objectIn.close();
    }

    public static void writePaymentMethods(ArrayList paymentMethods) throws IOException{
        FileOutputStream fileOut = new FileOutputStream(filePath(PAYMENTLIST));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(paymentMethods);
        objectOut.close();
    }


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

    private static String filePath(String fileName){
        URL url = Database.class.getResource(fileName);
        System.out.println(url);
        assert url != null;
        return url.getPath();
    }
}