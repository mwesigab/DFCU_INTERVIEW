package com.ben.interview.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigInteger;

@Table("CUSTOMERS")
public class Customer {
    @Id
    private BigInteger id;
    private String customerAcctNo;
    private String status;
    private String firstName;
    private String lastName;

    private String nin;

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getCustomerAcctNo() {
        return customerAcctNo;
    }

    public void setCustomerAcctNo(String customerAcctNo) {
        this.customerAcctNo = customerAcctNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
