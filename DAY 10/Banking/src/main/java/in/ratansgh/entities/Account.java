package in.ratansgh.entities;

import in.ratansgh.enums.AccountType;
import in.ratansgh.exception.InsufficientBalanceException;
import in.ratansgh.exception.InvalidDepositValueException;

import java.math.BigDecimal;
import java.util.Objects;

// we use abstract to share the implementation among other classes for example account details will be shared in transaction and all
public abstract class Account extends Customer {

    private String accountNo;
    private String customerID;
    private AccountType type;
    private BigDecimal balance;

    public Account() {

    }

    public Account(String accountNo, String customerID, AccountType type, BigDecimal balance) {
        this.accountNo = accountNo;
        this.customerID = customerID;
        this.type = type;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", customerID='" + customerID + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }

        // checking if it is of Account class
        if (getClass() != obj.getClass()){
            return false;
        }

        Account account = (Account) obj;
        return Objects.equals(accountNo , account.accountNo) && Objects.equals(customerID, account.customerID) && Objects.equals(type, account.type) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashcode(){
        return Objects.hash(accountNo);
    }

//    synchronized because we want to achieve atomicity
    public synchronized void deposit(BigDecimal amount){

        if ( amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidDepositValueException("Deposit value must be greater than 0");
        }
        this.balance = this.balance.add(amount);

    }

    public synchronized void withdraw(BigDecimal amount) {

        if ( amount.compareTo(balance) > 0 ){
            throw new InsufficientBalanceException("Insufficient Balance..");
        }
        this.balance = this.balance.subtract(amount);
    }


    // balance = 100 , interest = 4% then get interest in number i.e 4 so we (100 *4) / 100 => 4
    public BigDecimal calculateInterest(){
        return balance.multiply(getInterestRate()).divide(new BigDecimal(100));
    }

    public abstract BigDecimal getInterestRate();
    public abstract BigDecimal getMinimumBalance();

}
