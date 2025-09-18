package org.example.domain;

import java.time.LocalDate;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone_no;
    private LocalDate dob;

    public Customer(String customerId, LocalDate dob, String email, String name, String phone_no) {
        this.customerId = customerId;
        this.dob = dob;
        this.email = email;
        this.name = name;
        this.phone_no = phone_no;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                '}';
    }
}
