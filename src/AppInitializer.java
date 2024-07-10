import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AppInitializer {

    //==========Database area========= (access all around the project)
    static String[][] customer = new String[100][4];
    //--==========Database area=========
    static String[][] users = new String[3][2];

    static String[][] items = new String[100][4];
    static String[][] orders = new String[100][5]; // date,orderId,customer,total,item


    public static void main(String[] args) {

        // ============testing

        items[0][0] = "001";
        items[0][1] = "Desc 1";
        items[0][2] = "15";
        items[0][3] = "250";

        // --============testing

        // program initialization
        Scanner input = new Scanner(System.in);
        boolean exitState = false;

        String[] initializePageQuestions =
                {
                        "1) Do you want to login?",
                        "2) Are you new to here?",
                        "3) Do you want to exit the page?"
                };

        while (!exitState) {

            for (String question : initializePageQuestions) {
                System.out.println(question);
            }

            int userInput = input.nextInt();
            switch (userInput) {
                case 1:
                    if (login()) {
                        openDashboard();
                    }
                    printUi("Application");
                    break;
                case 2:
                    if (register()) {
                        openDashboard();
                    }
                    printUi("Application");
                    break;
                case 3:
                    System.out.println("Good bye !");
                    return;
                default:
                    System.out.println("Idiot!.. Wrong input..\uD83d\uDE14");
                    return; // exit
            }
        }
    }

    // login process
    public static boolean login() {
        printUi("Login");
        Scanner input = new Scanner(System.in);

        System.out.println("Please Enter the Email!");
        String email = input.nextLine();
        System.out.println("Please Enter the Password!");
        String password = input.nextLine();

        for (int i = 0; i < users.length; i++) {
            if (users[i][0] != null && users[i][0].equals(email)) {
                if (users[i][1].equals(password)) {
                    System.out.println("Welcome again!");
                    return true;
                } else {
                    System.out.println("Wrong Password!");
                    return false;
                }
            }
        }
        System.out.println("404 Not Found");
        return false;
    }
    // -- login process

    // register process
    public static boolean register() {
        Scanner input = new Scanner(System.in);

        if (users[users.length - 1][0] != null) {
            System.out.println("User database is full!");
            return false;
        }

        System.out.println("Insert Your Email!");
        String email = input.nextLine();
        System.out.println("Insert Your Password!");
        String password = input.nextLine();

        for (int x = 0; x < users.length; x++) {
            if (users[x][0] == null) {
                users[x][0] = email;
                users[x][0] = password;
                return true;
            } else {
                if (users[x][0].equalsIgnoreCase(email)) { // just ignore
                    System.out.println("Email is already exists!");
                    return false;
                }
            }
        }
        return false;
    }
    // -- register process

    // dashboard process
    public static void openDashboard() {
        Scanner input = new Scanner(System.in);
        String dashboardQuestions [] = {
                "1) Customer Management",
                "2) Item Management",
                "3) Order Management",
                "4) Logout",
        };
        while (true){
            for (String question: dashboardQuestions){
                System.out.println(question);
            }
            int userInput = input.nextInt();

            switch (userInput){
                case 1:
                    customerManagement();
                    break;
                case 2:
                    ItemManagement();
                    break;
                case 3:
                    placeNewOrder();
                    break;
                case 4:
                    return;
                default:
                    return;
            }
        }
    }
    // -- dashboard process

    // Customer process
    public static void customerManagement(){
        Scanner input = new Scanner(System.in);
        String customerQuestions [] = {
                "1) Save Customer",
                "2) Find Customer",
                "3) Update Customer",
                "4) Delete Customer",
                "5) Find All Customer",
                "6) Back to Home",
        };
        while (true){
            for (String question: customerQuestions){
                System.out.println(question);
            }
            int userInput = input.nextInt();

            switch (userInput){
                case 1:
                    saveCustomer();
                    break;
                case 2:
                    findCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    printAllCustomers();
                    break;
                case 6:
                    return;
                default:
                return;
            }
        }
    }

    public static void saveCustomer() {
        Scanner input = new Scanner(System.in);
        while(true) {
            String nic, name, address;
            double salary;
            System.out.println("Insert Customer NIC");
            nic = input.nextLine();
            System.out.println("Insert Customer Name");
            name = input.nextLine();
            System.out.println("Insert Customer Address");
            address = input.nextLine();
            System.out.println("Insert Customer Salary");
            salary= input.nextDouble();

            customerForLoop:
            for (int i = 0; i < customer.length; i++) {
                if(customer[i][0] != null){
                    if(customer[i][0].equals(nic)){
                        System.out.println("Customer Already Exists!");
                        break;
                    }
                }else {
                    customer[i][0] = nic;
                    customer[i][1] = name;
                    customer[i][2] = address;
                    customer[i][3] = String.valueOf(salary);

                    System.out.println("Customer Saved!\n");
                    System.out.println("1) Do you want to add an another customer?");
                    System.out.println("2) Back to Main Menu");

                    int option = input.nextInt();
                    switch (option) {
                        case 1:
                            break customerForLoop;
                        case 2:
                            return;
                        case 3:
                            return;
                    }
                }
            }
        }
    }

    public static void findCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert NIC to find the customer");
        String nic = input.nextLine();
        for (int i = 0; i < customer.length; i++) {
            if(customer[i][0] != null) {
                if(customer[i][0].equals(nic)){
                    System.out.println("================ Customer ================");
                    System.out.println("NIC : "+customer[i][0]);
                    System.out.println("Name : "+customer[i][1]);
                    System.out.println("Address : "+customer[i][2]);
                    System.out.println("Salary : "+customer[i][3]);
                    System.out.println("================ Customer ================");
                    return;
                }
            }
        }
        System.out.println("Customer Not Found");
    }

    public static void updateCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert NIC to find the customer");
        String nic = input.nextLine();
        for (int i = 0; i < customer.length; i++) {
            if(customer[i][0] != null) {
                if(customer[i][0].equals(nic)){
                    String newName, newAddress;
                    double newSalary;
                    System.out.println("Insert Customer Name to update");
                    newName = input.nextLine();
                    System.out.println("Insert Customer Address to update");
                    newAddress = input.nextLine();
                    System.out.println("Insert Customer Salary to update");
                    newSalary= input.nextDouble();

                    customer[i][1] = newName;
                    customer[i][2] = newAddress;
                    customer[i][3] = String.valueOf(newSalary);
                    System.out.println("Customer Updated!");

                    return;

                }
            }
        }
        System.out.println("Customer Not Found");
    }

    public static void deleteCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert NIC to delete the customer");
        String nic = input.nextLine();
        for (int i = 0; i < customer.length; i++) {
            if(customer[i][0] != null) {
                if(customer[i][0].equals(nic)){
                    customer[i][0] = null;
                    customer[i][1] = null;
                    customer[i][2] = null;
                    customer[i][3] = null;
                    System.out.println("Customer Deleted!");

                    return;

                }
            }
        }
        System.out.println("Customer Not Found");
    }

    public static void printAllCustomers() {
        for (int i = 0; i < customer.length; i++) {
            if(customer[i][0] != null){
                System.out.println("Nic: " + customer[i][0] + "\tName: " + customer[i][1] + "\tAddress: " + customer[i][2] + "\tSalary: " + customer[i][3]);
            }
        }
    }

    // --Customer process

    // Item process
    public static void ItemManagement(){
        Scanner input = new Scanner(System.in);
        String ItemQuestions [] = {
                "1) Save Item",
                "2) Find Item",
                "3) Update Item",
                "4) Delete Item",
                "5) Find All Item",
                "6) Back to Home",
        };
        while (true){
            for (String question: ItemQuestions){
                System.out.println(question);
            }
            int userInput = input.nextInt();

            switch (userInput){
                case 1:
                    saveItem();
                    break;
                case 2:
                    findItem();
                    break;
                case 3:
                    updateItem();
                    break;
                case 4:
                    deleteItem();
                    break;
                case 5:
                    printAllItems();
                    break;
                case 6:
                    return;
                default:
                    return;
            }
        }
    }

    public static void saveItem() {
        Scanner input = new Scanner(System.in);
        while(true) {
            String code, description;
            int qtyOnHand;
            double unitPrice;
            System.out.println("Insert Item Code");
            code = input.nextLine();
            System.out.println("Insert Item Description");
            description = input.nextLine();
            System.out.println("Insert Item Qty On Hand");
            qtyOnHand = input.nextInt();
            System.out.println("Insert Item Unit Price");
            unitPrice= input.nextDouble();

            itemsForLoop:
            for (int i = 0; i < items.length; i++) {
                if(items[i][0] != null){
                    if(items[i][0].equals(code)){
                        System.out.println("Item Already Exists!");
                        break;
                    }
                }else {
                    items[i][0] = code;
                    items[i][1] = description;
                    items[i][2] = String.valueOf(qtyOnHand);
                    items[i][3] = String.valueOf(unitPrice);

                    System.out.println("Item Saved!\n");
                    System.out.println("1) Do you want to add an another items?");
                    System.out.println("2) Back to Main Menu");

                    int option = input.nextInt();
                    switch (option) {
                        case 1:
                            break itemsForLoop;
                        case 2:
                            return;
                        case 3:
                            return;
                    }
                }
            }
        }
    }

    public static void findItem() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Code to find the items");
        String code = input.nextLine();
        for (int i = 0; i < items.length; i++) {
            if(items[i][0] != null) {
                if(items[i][0].equals(code)){
                    System.out.println("================ Item ================");
                    System.out.println("Code : "+items[i][0]);
                    System.out.println("Description : "+items[i][1]);
                    System.out.println("Qty On Hand : "+items[i][2]);
                    System.out.println("UnitPrice : "+items[i][3]);
                    System.out.println("================ Item ================");
                    return;
                }
            }
        }
        System.out.println("Item Not Found");
    }

    public static void updateItem() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Code to find the items");
        String code = input.nextLine();
        for (int i = 0; i < items.length; i++) {
            if(items[i][0] != null) {
                if(items[i][0].equals(code)){
                    String newDescription;
                    int newQtyOnHand;
                    double newUnitPrice;
                    System.out.println("Insert Item Description to update");
                    newDescription = input.nextLine();
                    System.out.println("Insert Item Qty On Hand to update");
                    newQtyOnHand = input.nextInt();
                    System.out.println("Insert Item Unit Price to update");
                    newUnitPrice= input.nextDouble();

                    customer[i][1] = newDescription;
                    customer[i][2] = String.valueOf(newQtyOnHand);
                    customer[i][3] = String.valueOf(newUnitPrice);
                    System.out.println("Item Updated!");

                    return;

                }
            }
        }
        System.out.println("Item Not Found");
    }

    public static void deleteItem() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Code to delete the items");
        String code = input.nextLine();
        for (int i = 0; i < items.length; i++) {
            if(items[i][0] != null) {
                if(items[i][0].equals(code)){
                    items[i][0] = null;
                    items[i][1] = null;
                    items[i][2] = null;
                    items[i][3] = null;
                    System.out.println("Item Deleted!");

                    return;

                }
            }
        }
        System.out.println("Customer Not Found");
    }

    public static void printAllItems() {
        for (int i = 0; i < items.length; i++) {
            if(items[i][0] != null){
                System.out.println("Code: " + items[i][0] + "\tDescription: " + items[i][1] + "\tQty On Hand: " + items[i][2] + "\tUnit Price: " + items[i][3]);
            }
        }
    }

    // --Item process

    // Order process

    public static void placeNewOrder() {
        Scanner input = new Scanner(System.in);

        // =========== Customer find ===========

        System.out.println("Insert Customer Nic");

        String nic = input.nextLine();

        String name, address;
        double salary;

        for (int i = 0; i < customer.length; i++) {
            if(customer[i][0] != null) {
                if(customer[i][0].equals(nic)){
                    System.out.println("================ Customer ================");
                    name = customer[i][1];
                    address = customer[i][2];
                    salary = Double.parseDouble(customer[i][3]);
                    System.out.println("================ Customer ================");

                }
            }
        }

        // --=========== Customer find ===========

        // =========== Item find ===========

        System.out.println("Insert Item Code");

        String code = input.nextLine();

        String description;
        double unitPrice = 0;
        int qtyOnHand;

        for (int i = 0; i < items.length; i++) {
            if(items[i][0] != null) {
                if(items[i][0].equals(code)){
                    System.out.println("================ Items ================");
                    description = items[i][1];
                    qtyOnHand = Integer.parseInt(items[i][2]);
                    unitPrice = Double.parseDouble(customer[i][3]);
                    System.out.println("================ Items ================");

                }
            }
        }

        // --=========== Item find ===========

        // =========== Place Order ===========

        System.out.println("Insert Order Code");

        String orderId = input.nextLine();

        for (int i = 0; i < orders.length; i++) {
            if(orders[i][0] != null) {
                if(orders[i][0].equals(orderId)){
                    System.out.println("Order id exists");
                    return;
                }else {
                    Date date = new Date();
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    String selectedDate = f.format(date);
                    orders[i][0] = orderId;
                    orders[i][1] = nic;
                    orders[i][2] = code;
                    orders[i][3] = selectedDate;
                    orders[i][3] = String.valueOf(unitPrice);
                }
            }
        }

        System.out.println("Order Completed!");

        // --=========== Place Order ===========

    }


    // --Order process

    public static void printUi(String position){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        String simpleDate = dateFormat.format(date);
        String simpleTime = timeFormat.format(date);

        System.out.println("=========="+simpleDate+"=========="+simpleTime+"==>"+position);

    }

}
