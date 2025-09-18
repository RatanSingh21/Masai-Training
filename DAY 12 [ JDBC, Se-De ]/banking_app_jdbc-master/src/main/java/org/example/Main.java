package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.example.domain.Customer;
import org.example.domain.Transaction;
import org.example.domain.accounts.Account;
import org.example.domain.accounts.CurrentsAccount;
import org.example.domain.accounts.SavingsAccount;
import org.example.domain.enums.TransactionType;
import org.example.util.DBconnectionUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println("Welcome to HDFC banking application");


        try {
            while (true)
            {
                showMainMenu();
            }
        }catch (Exception e)
        {
            System.out.println("Error :"+e.getMessage());
        }finally {
            sc.close();
        }
    }

    private static void showMainMenu(){

        System.out.println("\n==Main Menu==");
        System.out.println("1. Register New Customer");
        System.out.println("2. Create Account");
        System.out.println("3. Perform Transactions");
        System.out.println("4. View Account Details");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");

        System.out.println("Enter your choice");

        int choice = getInput();

        switch (choice)
        {
            case 1:registerCustomer();
                break;

            case 2:
                createAccount();
                break;

            case 3:
                performTransaction();
                break;

            case 4:
                allAccounts();
                break;

            case 5:
                //viewTransactionHistory();
                break;


        }


    }

    private static void registerCustomer(){

        System.out.println("\nRegister Customer");

        System.out.println("Enter Customer id:");
        String customerId = sc.nextLine().trim();

//        if (customers.containsKey(customerId)){
//            System.out.println("Customer Already exists");
//        }

        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();

        System.out.println("Enter email: ");
        String email = sc.nextLine().trim();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            System.out.println("Valid email");
        } else {
            System.out.println("Invalid email");
            return;
        }


        System.out.println("Enter phone no: ");
        String phone_no = sc.nextLine().trim();
        String phoneRegex = "^(?:\\+91|91|0)?[6-9]\\d{9}$";
        Pattern patternP = Pattern.compile(phoneRegex);
        Matcher matcherP = patternP.matcher(phone_no);

        if (matcherP.matches()) {
            System.out.println("Valid Phone no");
        } else {
            System.out.println("Invalid Phone no");
            return;
        }

        System.out.println("Enter dob in yyyy-mm-dd: ");
        String dobStr = sc.nextLine().trim();

        LocalDate dateOfBirth;

        try {
            dateOfBirth = LocalDate.parse(dobStr,dateFormatter);
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return;
        }


        Customer customer = new Customer(customerId,dateOfBirth,email,name,phone_no);


        DBconnectionUtil util = new DBconnectionUtil();
        try {
            String status = util.insertCustomer(customer);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("Customer Registered Succesfully");

    }


    private static void createAccount(){
        System.out.println("===Creating new Account===");
        System.out.println("Please enter customer id");
        String customerId = sc.next().trim();
        sc.nextLine();

      //  Customer customer = customers.get(customerId);

//        if (customer==null)
//        {
//            System.out.println("Customer not found");
//            return;
//        }

        System.out.println("choose Account type");
        System.out.println("1. Savings Account(6% interest rate and minimum balance 1000");
        System.out.println("2. Current Account(4% interest rate and no minimum balance");

        int typeChoice = getInput();
        Account account = null;

        System.out.println("Enter initial Balance");
        String balance = sc.nextLine().trim();

        try {
            BigDecimal initialBalance = new BigDecimal(balance);
            String accountNo = generateAccountNo();

            switch (typeChoice){
                case 1:
                    account = new SavingsAccount(accountNo,initialBalance,customerId);
                    break;
                case 2:
                    account = new CurrentsAccount(accountNo, initialBalance,customerId);
                    break;
                default:
                    System.out.println("invalid account typpe");
                    return;
            }
        }catch (Exception e)
        {
            System.out.println("Initial balance error");
        }

        try {
            DBconnectionUtil util = new DBconnectionUtil();
            util.insertAccount(account);
            //accounts.put(accountNo,account);
            System.out.println("Account Created Successfull AccountNo: "+account.getAccountNo());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    private static String generateAccountNo(){
        return String.format("%010d", System.currentTimeMillis() % 10000000000L);
    }


    private static void performTransaction() {
        System.out.println("\n=====Perform Transaction===");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");

        System.out.println("Select Transaction type");
        int transactionChoice = getInput();

        switch (transactionChoice){
            case 1:
                try {
                    performDeposit();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    performWithdraw();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }


    private static void performDeposit() throws SQLException {
        DBconnectionUtil util = new DBconnectionUtil();
        System.out.println("Enter Account No: ");
        String accountNo = sc.nextLine().trim();


        if (util.accountLookup(accountNo)==null) {
            System.out.println("Account not found");
            return;
        }

        BigDecimal balance = new BigDecimal(util.accountLookup(accountNo));

        System.out.println("Enter Deposit Amount: ");
        String amountStr = sc.nextLine().trim();

        BigDecimal amountOld = new BigDecimal(amountStr);
        try {
            BigDecimal amount = new BigDecimal(amountStr).add(balance);
            util.deposit(accountNo, amount);
            String transactionId = generateTransactionId();
            Timestamp ts = new Timestamp(System.currentTimeMillis()/1000);

            Transaction transaction = new Transaction(accountNo,amountOld,ts,transactionId, TransactionType.DEPOSIT.name());
            util.insertTransaction(transaction);

            System.out.println("Deposit Done successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid Amount");
        }
    }

    private static void performWithdraw() throws SQLException {
        DBconnectionUtil util = new DBconnectionUtil();
        System.out.println("Enter Account No: ");
        String accountNo = sc.nextLine().trim();


        if (util.accountLookup(accountNo)==null) {
            System.out.println("Account not found");
            return;
        }

        BigDecimal balance = new BigDecimal(util.accountLookup(accountNo));

        System.out.println("Enter Withdrawal Amount: ");
        String amountStr = sc.nextLine().trim();

        if(Integer.parseInt(amountStr)>balance.intValue())
        {
            System.out.println("Withdrawal amount cannot be greater than balance");
            return;
        }
        try {
            BigDecimal amount = new BigDecimal(amountStr);
            util.deposit(accountNo, balance.subtract(amount));
            String transactionId = generateTransactionId();
            Timestamp ts = new Timestamp(System.currentTimeMillis()/1000);

            Transaction transaction = new Transaction(accountNo,amount,ts,transactionId, TransactionType.WITHDRAW.name());
            util.insertTransaction(transaction);

            System.out.println("Withdrawal Done successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid Amount");
        }
    }

    private static void allAccounts(){
        DBconnectionUtil util = new DBconnectionUtil();
        try {
            util.allAccounts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    private static String generateTransactionId(){
        return "HDFC_TXN"+System.currentTimeMillis()/1000;
    }

    private static int getInput(){
        while (true){
            try {
                return Integer.parseInt(sc.nextLine().trim());
            }catch (NumberFormatException e){
                System.out.println("Please enter valid number");
            }
        }
    }

}
