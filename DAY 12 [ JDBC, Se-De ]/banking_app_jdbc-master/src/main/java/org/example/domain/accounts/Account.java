package org.example.domain.accounts;

import org.example.domain.enums.AccountType;

import java.math.BigDecimal;

public abstract class Account {

    private String accountNo;
    private String customerId;
    private AccountType accountType;
    private BigDecimal balance;

    public Account(String accountNo, AccountType accountType, BigDecimal balance, String customerId) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
        this.customerId = customerId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", customerId='" + customerId + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }

    public abstract BigDecimal getInterest();
    public abstract BigDecimal getMinBalance();
}
