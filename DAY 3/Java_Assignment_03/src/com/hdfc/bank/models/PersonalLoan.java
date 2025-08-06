package com.hdfc.bank.models;

public class PersonalLoan extends Loan{

    private String purpose;

    public PersonalLoan(int LoanId, String CustomerName, double LoanAmount , String purpose) {
        super(LoanId, CustomerName,LoanAmount);
        this. purpose = purpose;
    }

    // formula for EMI calculation
    @Override
    public double calculateEMI() {
        double interest =  getLoanAmount() * 12 * 2;
        double totalAmount = getLoanAmount() * interest;
        double EMI = totalAmount * 24; // 2 yrs

        return EMI;
    }

    public void printDetails(){
        super.printDetails();
        System.out.println("The purpose of the loan is : " + purpose);

    }
}
