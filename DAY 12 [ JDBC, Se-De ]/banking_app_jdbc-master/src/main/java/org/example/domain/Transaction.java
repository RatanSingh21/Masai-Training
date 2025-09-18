package org.example.domain;

import org.example.domain.accounts.Account;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
    private String txn_id;
    private String account_no;
    private String txn_type;
    private BigDecimal amount;
    private Timestamp timestamp;

    public Transaction(String account_no, BigDecimal amount, Timestamp timestamp, String txn_id, String txn_type) {
        this.account_no = account_no;
        this.amount = amount;
        this.timestamp = timestamp;
        this.txn_id = txn_id;
        this.txn_type = txn_type;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public String getTxn_type() {
        return txn_type;
    }

    public void setTxn_type(String txn_type) {
        this.txn_type = txn_type;
    }
//
//    public void deposit(Account account, BigDecimal amount)
//    {
//        account.setBalance(account.getBalance().add(amount));
//
//    }
//    public void withdraw(Account account, BigDecimal amount)
//    {
//        account.setBalance(account.getBalance().subtract(amount));
//
//    }
}
