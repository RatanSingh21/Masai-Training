package in.ratansgh.entities;

import in.ratansgh.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private String transactionID;
    private BigDecimal amount;
    private String accountNo;
    private LocalDateTime timestamp;
    private TransactionType type;

    public Transaction(){

    }

    public Transaction(String transactionID, BigDecimal amount, String accountNo, LocalDateTime timestamp, TransactionType type) {
        this.transactionID = transactionID;
        this.amount = amount;
        this.accountNo = accountNo;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID='" + transactionID + '\'' +
                ", amount=" + amount +
                ", accountNo='" + accountNo + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                '}';
    }

//    @Override
    public int hashcode(){
        return Objects.hash(transactionID);
    }

    @Override
    public boolean equals(Object obj){

        if (obj == null) {
            return false;
        }
        if(this == obj){
            return true;
        }

        Transaction transaction = (Transaction) obj;

        return Objects.equals(transactionID , transaction.transactionID);
    }
}
