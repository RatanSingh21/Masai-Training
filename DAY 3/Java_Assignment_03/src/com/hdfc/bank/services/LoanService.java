package com.hdfc.bank.services;


import com.hdfc.bank.models.HomeLoan;
import com.hdfc.bank.models.Loan;
import com.hdfc.bank.models.PersonalLoan;

import java.sql.SQLOutput;

public class LoanService {
    public void printEMIDetails(Loan loan){

        if(loan instanceof HomeLoan){
            HomeLoan CustHL_01 = (HomeLoan) loan;
            CustHL_01.printDetails();
        } else if (loan instanceof PersonalLoan) {
            PersonalLoan CustPL_02 =(PersonalLoan) loan;
            CustPL_02.printDetails();
        }

        System.out.println("The EMI of the " + loan + "is " + loan.calculateEMI());
    }
}
