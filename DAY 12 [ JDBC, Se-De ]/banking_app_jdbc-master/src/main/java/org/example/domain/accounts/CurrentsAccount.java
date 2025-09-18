package org.example.domain.accounts;

import org.example.domain.enums.AccountType;

import java.math.BigDecimal;

public class CurrentsAccount extends Account{

    private final static BigDecimal minBalance = new BigDecimal("1000");
    private final static BigDecimal interestRate = new BigDecimal("10");

    public CurrentsAccount(String accountNo, BigDecimal balance, String customerId) {
        super(accountNo, AccountType.CURRENT, balance, customerId);
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
