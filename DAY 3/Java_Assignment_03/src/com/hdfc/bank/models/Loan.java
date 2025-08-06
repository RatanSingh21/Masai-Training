package com.hdfc.bank.models;

public abstract class Loan {
//    Fields: loanId (int), customerName (String), loanAmount (double)
    private int loanId;
    protected String customerName;
    protected double loanAmount;

    // Getter and Setter
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    // Constructor
    public Loan(int loanId, String customerName, double loanAmount) {
        this.loanId = loanId;
        this.customerName = customerName;
        this.loanAmount = loanAmount;
    }

    // Abstract class
    public abstract double calculateEMI();

    // Non Abstract class
    public void printDetails(){
        System.out.println("The Loan informations are as follows: ");
        System.out.println("LoanId: " + loanId);
        System.out.println("CustomerName: " + customerName);
        System.out.println("LoanAmount: " +loanAmount);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", customerName='" + customerName + '\'' +
                ", loanAmount=" + loanAmount +
                '}';
    }
}
