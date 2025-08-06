package com.hdfc.bank.models;

public class HomeLoan extends Loan{

    private String propertyAddress;

    public HomeLoan(int LoanId, String CustomerName, double LoanAmount, String propertyAddress) {
        super(LoanId, CustomerName,LoanAmount);
        this.propertyAddress = propertyAddress;
    }

    // formula for EMI calculation
    @Override
    public double calculateEMI() {
        double interest =  getLoanAmount() * 8.9 * 2;
        double totalAmount = getLoanAmount() * interest;
        double EMI = totalAmount * 24; // 2 yrs

        return EMI;
    }

    public void printDetails(){
        super.printDetails();
        System.out.println("Home Address: " + propertyAddress);
    }

    // Method Overloading
    public void applyInsurance(){
        System.out.println("This is normal apply insurance method...");
    }

    public void applyInsurance(String CustomerName){
        System.out.println("The " + getCustomerName() + " is applying for insurance method...");
    }

}
