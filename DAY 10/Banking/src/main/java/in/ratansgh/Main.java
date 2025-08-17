package in.ratansgh;

import in.ratansgh.entities.Customer;
import in.ratansgh.entities.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    //        Map parent of HashMap, good practice to declare in this way..
    private static final Map<String, Customer> customers = new HashMap<>();
    private static final Map<String, Customer> accounts = new HashMap<>();

    //        List parent of ArrayList
    private static final List<Transaction> transactions = new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    private static final DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");

    public static void main(String[] args) {

        System.out.println("Welcome to HDFC BANKING APPLICATION....");

        // calling the menu
        try {
            while(true){
                showmainMenu();
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }

    }

    public static void showmainMenu(){

        System.out.println("\n------------------ MAIN MENU ------------------");
        System.out.println("1. Register new customer...");
        System.out.println("2. Create new account...");
        System.out.println("3. Perform Transaction...");
        System.out.println("4. View Account Details...");
        System.out.println("5. View Transaction History...");
        System.out.println("6. Exit..");

        System.out.println(
                "Enter your choices: "
        );

        int choice = getInput();

        switch (choice){
            case 1:
                registerCustomer();
                break;
            case 2:
        }
    }

    private static int getInput(){
        while (true){
            try{
                return Integer.parseInt((sc.nextLine().trim()));
            } catch (NumberFormatException e){
                System.out.println("Please enter a valid number");
            }
        }
    }

    public static void registerCustomer(){
        System.out.println("\n ---------- Customer Registation --------");

        System.out.println("Enter Customer ID: ");
        String customerID = sc.nextLine().trim();

        if (customers.containsKey(customerID)){
            System.out.println("Customer already exists");
            return;
        }
        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();

        System.out.println("Enter Email: ");
        String email = sc.nextLine().trim();

        System.out.println("Enter Mobile Number: ");
        String phone = sc.nextLine().trim();

        System.out.println("Enter the DOB in yyyy-mm-dd: ");
        String DOB = sc.nextLine().trim();

        LocalDate dateOfBirth;
        try{
            dateOfBirth = LocalDate.parse(DOB , dateformatter);
        } catch (Exception e){
            System.out.println("Invalid date format..");
            return;
        }

        System.out.println("Enter the password: ");
        String password = sc.nextLine().trim();

        Customer customer = new Customer(customerID,name,email,phone,password,dateOfBirth);
        customers.put(customerID,customer);

        System.out.println("Customer registered Successfully....");
    }

}