package org.example.domain.accounts;

import org.example.domain.enums.AccountType;

import java.math.BigDecimal;

public class SavingsAccount extends Account{


    private final static BigDecimal minBalance = new BigDecimal("0");
    private final static BigDecimal interestRate = new BigDecimal("7");

    public SavingsAccount(String accountNo,  BigDecimal balance, String customerId) {
        super(accountNo, AccountType.SAVINGS, balance, customerId);
    }

    @Override
    public BigDecimal getInterest() {
        return interestRate;
    }

    @Override
    public BigDecimal getMinBalance() {
        return minBalance;
    }
}
