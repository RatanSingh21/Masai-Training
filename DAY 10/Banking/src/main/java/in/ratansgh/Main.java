package in.ratansgh;

import in.ratansgh.entities.*;
import in.ratansgh.enums.AccountType;
import in.ratansgh.enums.TransactionType;
import in.ratansgh.exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    //        Map parent of HashMap, good practice to declare in this way..
    private static final Map<String, Customer> customers = new HashMap<>();
    private static final Map<String, Account> accounts = new HashMap<>();

    //        List parent of ArrayList
    private static final List<Transaction> transactions = new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
                createAccount();
                break;
            case 3:
                performTransaction();
                break;
            case 4:
                viewAccountDetails();
                break;
            case 5:
                viewTransactionHistory();
                break;
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

        System.out.println("Enter the DOB in yyyy-MM-dd: ");
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

    private static void createAccount(){

        System.out.println("\n ---------- Create New Account  --------");

        System.out.println("Please enter Customer Id");
        String customerId=sc.nextLine().trim();

        Customer customer=customers.get(customerId);
        if(customer ==null ){
            System.out.println("Customer not found");
            return;
        }

        System.out.println("Choose account type: ");
        System.out.println("1) Savings Account (6% Interest rate and min balance of 10,000");
        System.out.println("2)Current Account (6 % Interest rate and no min balance) ");
        System.out.println("Select Account type");

        int typeChoice =getInput();
        Account account=null;

        System.out.println("Enter initial balance: ");
        String balance =sc.nextLine().trim();

        try {
            BigDecimal initialBalance = new BigDecimal(balance);
            String accountNo = generateAccountNo();

            switch (typeChoice) {
                case 1:
                    account = new SavingAccount(accountNo,customerId, AccountType.SAVINGS,initialBalance);
                    break;
                case 2:
                    account = new CurrentAccount(accountNo, customerId, AccountType.CURRENT,initialBalance);
                default:
                    System.out.println("Invalid Account type...");
                    return;
            }
            accounts.put(accountNo, account);
            System.out.println("Congratulation Account created successfully; with the account no: " + accountNo);

        } catch (NumberFormatException e) {
            System.out.println(
                    "Invalid Balance amount!"
            );
        }

    }

    private static String generateAccountNo() {
        return String.format("%10d ", System.currentTimeMillis()%100000000L);
    }

    private static void performTransaction(){

        System.out.println("\n ---------- Perform Transaction  --------");
        System.out.println("1. Deposit ");
        System.out.println("2. Withdraw ");
        System.out.println("3. Transfer ");

        System.out.println("PLease select the type of transaction..");

        int transactionChoice = getInput();

        switch (transactionChoice){
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdraw();
                break;
            case 3:
                performTransfer();
                break;
            default:
                System.out.println("Invalid Transaction type!! ");
        }
    }

    private static void performDeposit() {

        System.out.println("Enter account Number: ");
        String accountNo = sc.nextLine().trim();

        Account account = accounts.get(accountNo);

        if (account == null) {
            System.out.println("Account Not found! ");
            return;
        }

        System.out.println("Enter the deposit amount...");
        String amountstr = sc.nextLine().trim();

        try{
            BigDecimal amount = new BigDecimal(amountstr);

            account.deposit(amount);

            String transactionID = generateTransactionID();

            Transaction transaction = new Transaction(transactionID,amount,accountNo,LocalDateTime.now(),TransactionType.DEPOSIT);


            transactions.add(transaction);
            System.out.println("Deposit was successfully !!! New Balance: " + account.getBalance());
        }catch (NumberFormatException e) {
            System.out.println("Invalid Amount....");
        }


    }

    public static String generateTransactionID(){

        return "HDFC_TXN " + System.currentTimeMillis();
    }

    private static void performWithdraw() {

        System.out.println("Enter Account Number: ");
        String accountNo = sc.nextLine().trim();

        Account account = accounts.get(accountNo);

        if (account == null) {
            System.out.println("Account registered nhi hai !!!");
            return;
        }

        System.out.println("Enter the withdrawl Amount: ");
        String amountstr = sc.nextLine().trim();

        try{
           BigDecimal amount = new BigDecimal(amountstr);

           account.withdraw(amount);

           String transactionId = generateTransactionID();

           Transaction transaction = new Transaction(transactionId,amount,accountNo,LocalDateTime.now(),TransactionType.WITHDRAW);

            System.out.println("Withdrawl Successful! Balance " + account.getBalance());
        } catch (NumberFormatException e ){
            System.out.println("Invalid Amount");
        } catch (InsufficientBalanceException e){
            System.out.println("Error : " + e.getMessage());
        }
    }


    private static void performTransfer() {

        System.out.println("Enter the source account Number: ");
        String fromAccountNo = sc.nextLine().trim();

        Account fromAccount = accounts.get(fromAccountNo);
//        Account fromAccount = (Account) accounts.get(fromAccountNo);

        if (fromAccount == null){
            System.out.println(" Soucr acc not found !!");
            return;
        }


        System.out.println("Enter the Destination account Number: ");
        String toAccountNo = sc.nextLine().trim();

        Account toAccount = accounts.get(fromAccountNo);
//        Account toAccount = (Account) accounts.get(fromAccountNo);

        if (toAccount == null){
            System.out.println(" Destination acc not found !!");
            return;
        }


        if(fromAccountNo.equals(toAccountNo)){
            System.out.println("Can't Transfer to same account!!");
        }

        System.out.println(" Enter the amount: ");
        String amountstr = sc.nextLine().trim();


        try {

            BigDecimal amount = new BigDecimal(amountstr);

            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            String transactionId = generateTransactionID();
            Transaction transaction = new Transaction(transactionId,amount,fromAccountNo,LocalDateTime.now(),TransactionType.TRANSFER,toAccountNo); // to amount wala constrcutor


        }catch (NumberFormatException e){
            System.out.println("Invalid number");
        } catch (InsufficientBalanceException e){
            System.out.println(" Error: " + e.getMessage());
        }

    }

    private static void viewAccountDetails(){

        System.out.println("\n ---------- View Account Details --------");

        System.out.println("Enter the account number...");
        String accountNo = sc.nextLine().trim();

        Account account = accounts.get(accountNo);
        if (account==null){
            System.out.println("Account no found!!!");
            return;
        }

        System.out.println(account.toString());
        if (account instanceof SavingAccount){
            System.out.println("Account type: Saving Account...");
        }else {
            System.out.println("Account type: Current Account...");
        }
    }

    private static void viewTransactionHistory(){

        System.out.println("\n ---------- Transaction History --------");

        System.out.println("Kaunsa acccount ka transaction dekhna hai...");
        String accountNo = sc.nextLine().trim();

        Account account = accounts.get(accountNo);
        if (account == null){
            System.out.println("Account No FOUND!!! ");
            return;
        }

        List<Transaction> accountTransaction = transactions.stream()
                .filter(t -> t.getAccountNo().equals(accountNo))
                .sorted(Comparator.comparing(Transaction::getTimestamp).reversed())
                .toList();

        if (accountTransaction.isEmpty()){
            System.out.println("No Transaction found!!!");
        }

        for (Transaction transaction: accountTransaction){
            System.out.println(transaction.toString());
        }

        Map<TransactionType, Long> transactionSummary = accountTransaction.stream()
                .collect(Collectors.groupingBy(Transaction::getType, Collectors.counting()));

        System.out.println("\n ---------- Transaction Summary --------");
        transactionSummary.forEach((type,count)->
                System.out.println(type.getDisplayName() + " : " + count + " Transaction"));

    }

}