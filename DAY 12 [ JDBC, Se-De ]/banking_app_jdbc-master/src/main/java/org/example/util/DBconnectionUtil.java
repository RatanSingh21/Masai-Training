package org.example.util;

import org.example.domain.Customer;
import org.example.domain.Transaction;
import org.example.domain.accounts.Account;

import java.math.BigDecimal;
import java.sql.*;
import java.time.format.DateTimeFormatter;

public class DBconnectionUtil {

    private static Connection makeConnection() throws SQLException, ClassNotFoundException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/hddb";

        Connection connection = DriverManager.getConnection(url, "root", "Lmntrix@291103");
        if (connection != null) {
            System.out.println("connection established");
//
        }

        return connection;

    }

    public String insertCustomer(Customer customer) throws SQLException {

        Connection con;
        try {
            con = makeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String dobStr = customer.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        PreparedStatement pst = con.prepareStatement("INSERT INTO customer (customer_id, name, email, phone, dob) VALUES (?, ?, ?, ?, ?)");
        pst.setString(1,customer.getCustomerId());
        pst.setString(2, customer.getName());
        pst.setString(3,customer.getEmail());
        pst.setString(4,customer.getPhone_no());
        pst.setString(5, dobStr);

        pst.executeUpdate();
        return "Inserted Customer Successfully";
    }

    public void insertAccount(Account account) throws SQLException {
        Connection con;
        try {
            con = makeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PreparedStatement pst = con.prepareStatement("INSERT INTO account (account_no, customer_id, account_type, balance) VALUES (?, ?, ?, ?)");
        pst.setString(1,account.getAccountNo());
        pst.setString(2, account.getCustomerId());
        pst.setString(3,account.getAccountType().getDisplayName());
        pst.setBigDecimal(4,account.getBalance());
        pst.executeUpdate();
        System.out.println("Account entered db successs bleh im tired");;
    }


    public String accountLookup(String accountNo) throws SQLException {
        Connection con;
        try {
            con = makeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PreparedStatement pst = con.prepareStatement("select balance from account where account_no=?");
        pst.setString(1,accountNo);


        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getBigDecimal("balance").toString();
        } else {
            return null; // or "Account not found"
        }
    }

    public boolean customerLookup(String custNo) throws SQLException {
        Connection con;
        try {
            con = makeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PreparedStatement pst = con.prepareStatement("select customer_id from customer where customer_id=?");
        pst.setString(1,custNo);

        ResultSet rs = pst.executeQuery();

        return rs.next();
    }


    public void insertTransaction(Transaction transaction) throws SQLException {
        Connection con;
        try {
            con = makeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement pst = con.prepareStatement("INSERT INTO transaction (txn_id, account_no, txn_type, amount, `timestamp`)VALUES (?,?,?,?,?)");
        pst.setString(1,transaction.getTxn_id());
        pst.setString(2,transaction.getAccount_no());
        pst.setString(3,transaction.getTxn_type());
        pst.setBigDecimal(4,transaction.getAmount());
        pst.setTimestamp(5,transaction.getTimestamp());

        pst.executeUpdate();
    }

    public void deposit(String accountNo, BigDecimal amount) throws SQLException {
        Connection con;
        try {
            con = makeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement pst = con.prepareStatement("Update  account set balance=? where account_no=?");
        pst.setBigDecimal(1,amount);
        pst.setString(2,accountNo);
        pst.executeUpdate();
    }


    public void allAccounts() throws SQLException {
        Connection con;
        try {
            con = makeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement pst = con.prepareStatement("select * from account");
        ResultSet rs = pst.executeQuery();
        while(rs.next())
        {
            String id = rs.getString("account_no");
            String cid = rs.getString("customer_id");
            String acc_t = rs.getString("account_type");
            String balance = rs.getBigDecimal("balance").toString();

            System.out.println("Id is: "+id);
            System.out.println("Customer name is: "+cid);
            System.out.println("Account type: "+acc_t);
            System.out.println("Balance: "+balance);

            System.out.println();
        }
    }


}
