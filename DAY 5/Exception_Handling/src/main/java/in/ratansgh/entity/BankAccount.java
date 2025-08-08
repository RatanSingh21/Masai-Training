package in.ratansgh.entity;

import in.ratansgh.service.BankAccountService;

public class BankAccount {

    private int custID;
    private String custName;
    private long custAccountNumber;
    private double custAccountBalance;
    private double limit;;

    // Getter  and Setter
    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public long getCustAccountNumber() {
        return custAccountNumber;
    }

    public void setCustAccountNumber(long custAccountNumber) {
        this.custAccountNumber = custAccountNumber;
    }

    public double getCustAccountBalance() {
        return custAccountBalance;
    }

    public void setCustAccountBalance(double custAccountBalance) {
        this.custAccountBalance = custAccountBalance;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    // Constructor
    public BankAccount(){}

    public BankAccount(int custID, String custName, long custAccountNumber, double custAccountBalance, double limit) {
        this.custID = custID;
        this.custName = custName;
        this.custAccountNumber = custAccountNumber;
        this.custAccountBalance = custAccountBalance;
        this.limit = limit;

    }
}
