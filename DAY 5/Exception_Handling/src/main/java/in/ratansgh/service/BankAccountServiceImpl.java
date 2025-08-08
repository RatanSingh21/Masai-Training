package in.ratansgh.service;

import in.ratansgh.entity.BankAccount;
import in.ratansgh.exception.InsufficientBalance;

public class BankAccountServiceImpl implements BankAccountService {

    BankAccount account;

    public BankAccountServiceImpl(BankAccount account) {
        this.account = account;
    }

    @Override
    public double checkBalance(BankAccount account) {
        this.account = account ;
        System.out.println("The current balance in the account is: " + this.account.getCustAccountBalance());
        return this.account.getCustAccountBalance();
    }

    @Override
    public double withdrawAmount(BankAccount account, double amount) {

        if(account.getCustAccountBalance() < amount){
            try {
                System.out.println("Insufficient Balance in account..");
            } catch (InsufficientBalance e){
                System.out.println(e.getMessage());
            }
        } else{
            account.setCustAccountBalance(account.getCustAccountBalance() - amount);
        }
        System.out.println("After withdrawing " + amount + " the balance is : " + account.getCustAccountBalance());
        return account.getCustAccountBalance();

    }

    @Override
    public double depositAmount(BankAccount account, double amount) {
         account.setCustAccountBalance(account.getCustAccountBalance() + amount);
        System.out.println("After depositing " + amount + " the balance is: " + account.getCustAccountBalance());
        return account.getCustAccountBalance();
    }
}
