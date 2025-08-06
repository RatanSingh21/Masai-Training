package com.hdfc.bank.main;

import com.hdfc.bank.models.HomeLoan;
import com.hdfc.bank.models.Loan;
import com.hdfc.bank.models.PersonalLoan;
import com.hdfc.bank.services.LoanService;

public class Main {
    public static void main(String[] args) {

        Loan Homeloan = new HomeLoan(1,"Ratan Singh", 40000, "Rambhau");
        Loan PersonalLoan = new PersonalLoan(2, "Vig", 60000 ,"Wedding loan");

        LoanService ls = new LoanService();
        ls.printEMIDetails(Homeloan);
        ls.printEMIDetails(PersonalLoan);
    }
}