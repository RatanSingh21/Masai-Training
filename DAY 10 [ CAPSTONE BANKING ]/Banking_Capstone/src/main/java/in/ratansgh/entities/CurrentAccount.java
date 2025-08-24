package in.ratansgh.entities;

import in.ratansgh.enums.AccountType;

import java.math.BigDecimal;

public class CurrentAccount extends Account{

    public static final BigDecimal interest_rate = new BigDecimal(4);
    public static final BigDecimal minimum_balance = new BigDecimal(0);

    public CurrentAccount(){
        super();
        setType(AccountType.CURRENT);
    }

    // super calls the constructor of parent class...
    public CurrentAccount(String accountNo, String customerID, AccountType type, BigDecimal balance) {
        super(accountNo, customerID, type, balance);
    }

    @Override
    public BigDecimal getInterestRate() {
        return interest_rate;
    }

    @Override
    public BigDecimal getMinimumBalance() {
        return minimum_balance;
    }

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "accountNo='" + getAccountNo() + '\'' +
                ", customerID='" + getCustomerID() + '\'' +
                ", type=" + getType() +
                ", balance=" + getBalance() +
                '}';
    }

}
