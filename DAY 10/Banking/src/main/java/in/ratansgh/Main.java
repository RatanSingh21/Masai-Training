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

    //        Map parent of HashMap, good practice to declare in this way
    private static final Map<String, Customer> customers = new HashMap<>();
    private static final Map<String, Account> accounts = new HashMap<>();

    //        List parent of ArrayList
    private static final List<Transaction> transactions = new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    private static final DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");

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
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option selected... LOL!!!");
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
        System.out.println("\n ---------- Customer Registration --------");

        System.out.println("Enter Customer ID: ");
        String customerID = sc.nextLine().trim();
        // regex validations
        if (RegexValidations.matchesCustomRegex(customerID, "^CUST_\\d{1,5}$")){
            System.out.println("Invalid enter for CUSTOMER_ID.... \nShould in this format CUST_OOX.....");
            return;
        }
        if (customers.containsKey(customerID)){
            System.out.println("Customer already exists");
            return;
        }

        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();
        if (!RegexValidations.isValidString(name)){
            System.out.println("Name should contain (a-z) or (A-Z)");
            return;
        }

        System.out.println("Enter Email: ");
        String email = sc.nextLine().trim();
        if (!RegexValidations.isValidEmail(email)){
            System.out.println("Email should be like abc@demo.com..");
            return;
        }

        System.out.println("Enter Mobile Number: ");
        String phone = sc.nextLine().trim();
        if (!RegexValidations.isValidPhone(phone)){
            System.out.println("Phone number should be of length 10 and only numbers...");
            return;
        }

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
        if (RegexValidations.matchesCustomRegex(password, "^[a-zA-Z0-9]{6,12}$")){
            System.out.println("Password should contain alphanumerics and minimum 2 and maximum 12 characters");
            return;
        }

        Customer customer = new Customer(customerID,name,email,phone,password,dateOfBirth);
        customers.put(customerID,customer);

        System.out.println("Customer registered Successfully....");
    }

    private static void createAccount(){

        System.out.println("\n ---------- Create New Account  --------");

        System.out.println("Please enter Customer Id");
        String customerId=sc.next().trim();
        Customer customer=customers.get(customerId);
        if(customer ==null ){
            System.out.println("Customer not found");
            return;
        }

        System.out.println("Choose account type: ");
        System.out.println("1) Savings Account (6% Interest rate and min balance of 10,000");
        System.out.println("2) Current Account (6 % Interest rate and no min balance) ");
        System.out.println("Select Account type");

        int typeChoice =getInput();
        Account account=null;

        System.out.println("Enter initial balance: ");
        String balance =sc.nextLine().trim();
        if (RegexValidations.isValidString(balance)){
            System.out.println("Only string numbers are allowed...");
        }

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
            System.out.println("Congratulation Account created successfully; " + customer.getName()+  " has the account no: " + accountNo);

        } catch (NumberFormatException e) {
            System.out.println(
                    "Invalid Balance amount!"
            );
        }

    }

    private static String generateAccountNo() {
        return "HDFC_ACC" + System.currentTimeMillis();
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
        if (RegexValidations.matchesCustomRegex(accountNo, "^HDFC_ACC.*")){
            System.out.println("Account number should start with HDFC_ACC.....");
        }

        Account account = accounts.get(accountNo);
        if (account == null) {
            System.out.println("Account Not found! ");
            return;
        }

        System.out.println("Enter the deposit amount...");
        String amountstr = sc.nextLine().trim();
        if (RegexValidations.isValidString(amountstr)){
            System.out.println("amount should have only numbers....");
        }

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
        if (RegexValidations.matchesCustomRegex(accountNo, "^HDFC_ACC.*")){
            System.out.println("Account number should start with HDFC_ACC.....");
        }

        Account account = accounts.get(accountNo);
        if (account == null) {
            System.out.println("Account registered nhi hai !!!");
            return;
        }

        System.out.println("Enter the withdrawal Amount: ");
        String amountstr = sc.nextLine().trim();
        if (RegexValidations.isValidString(amountstr)){
            System.out.println("amount should have only numbers....");
        }

        try{
           BigDecimal amount = new BigDecimal(amountstr);
           account.withdraw(amount);

           String transactionId = generateTransactionID();
           Transaction transaction = new Transaction(transactionId,amount,accountNo,LocalDateTime.now(),TransactionType.WITHDRAW);

            System.out.println("Withdrawal Successful! Balance " + account.getBalance());
        } catch (NumberFormatException e ){
            System.out.println("Invalid Amount");
        } catch (InsufficientBalanceException e){
            System.out.println("Error : " + e.getMessage());
        }
    }


    private static void performTransfer() {

        System.out.println("Enter the source account Number: ");
        String fromAccountNo = sc.nextLine().trim();
        if (RegexValidations.matchesCustomRegex(fromAccountNo, "^HDFC_ACC.*")){
            System.out.println("Account number should start with HDFC_ACC.....");
        }

        Account fromAccount = accounts.get(fromAccountNo);
        if (fromAccount == null){
            System.out.println(" Source acc not found !!");
            return;
        }


        System.out.println("Enter the Destination account Number: ");
        String toAccountNo = sc.nextLine().trim();
        if (RegexValidations.matchesCustomRegex(toAccountNo, "^HDFC_ACC.*")){
            System.out.println("Account number should start with HDFC_ACC.....");
        }

        Account toAccount = accounts.get(fromAccountNo);
        if (toAccount == null){
            System.out.println(" Destination acc not found !!");
            return;
        }


        if(fromAccountNo.equals(toAccountNo)){
            System.out.println("Can't Transfer to same account!!");
        }

        System.out.println(" Enter the amount: ");
        String amountstr = sc.nextLine().trim();
        if (!RegexValidations.isValidString(amountstr)){
            System.out.println("amount should have only numbers....");
        }

        try {

            BigDecimal amount = new BigDecimal(amountstr);

            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            String transactionId = generateTransactionID();
            Transaction transaction = new Transaction(transactionId,amount,fromAccountNo,LocalDateTime.now(),TransactionType.TRANSFER,toAccountNo); // to amount wala constrcutor
            System.out.println("Transaction completed successfully....");

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
        if (RegexValidations.matchesCustomRegex(accountNo, "^HDFC_ACC.*")){
            System.out.println("Account number should start with HDFC_ACC.....");
        }

        Account account = accounts.get(accountNo);
        if (account==null){
            System.out.println("Account no found!!!");
            return;
        }

        System.out.println(account.toString());
//        if (account instanceof SavingAccount){
//            System.out.println("Account type: Saving Account...");
//        }else {
//            System.out.println("Account type: Current Account...");
//        }
    }

    private static void viewTransactionHistory(){

        System.out.println("\n ---------- Transaction History --------");

        System.out.println("Which account's transaction you want to see ? Enter the account number...");
        String accountNo = sc.nextLine().trim();
        if (RegexValidations.matchesCustomRegex(accountNo, "^HDFC_ACC.*")){
            System.out.println("Account number should start with HDFC_ACC.....");
        }

        Account account = accounts.get(accountNo);
        if (account == null){
            System.out.println("Account No FOUND!!! ");
            return;
        }

        // filter using Lambda function.
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