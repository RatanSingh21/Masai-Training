package in.ratansgh.service;

import in.ratansgh.entity.BankAccount;

public interface BankAccountService {

    public double checkBalance(BankAccount account);
    public double withdrawAmount(BankAccount account, double amount);
    public double depositAmount(BankAccount account, double amount);

}
