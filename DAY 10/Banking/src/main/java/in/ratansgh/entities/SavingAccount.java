package in.ratansgh.entities;

import in.ratansgh.enums.AccountType;

import javax.swing.*;
import java.math.BigDecimal;

public class SavingAccount extends Account{

    public static final BigDecimal interest_rate = new BigDecimal(6);
    public static final BigDecimal minimum_balance = new BigDecimal(1000);

    public SavingAccount(){
        super();
        setType(AccountType.SAVINGS);
    }

    public SavingAccount(String accountNo, String customerID, AccountType type, BigDecimal balance) {
        super(accountNo, customerID, type, balance);
    }

    @Override
    public BigDecimal getInterestRate() {
        return null;
    }

    @Override
    public BigDecimal getMinimumBalance() {
        return null;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "accountNo='" + getAccountNo() + '\'' +
                ", customerID='" + getCustomerID() + '\'' +
                ", type=" + getType() +
                ", balance=" + getBalance() +
                '}';
    }
}
