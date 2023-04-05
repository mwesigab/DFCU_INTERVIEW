package com.ben.interview.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Table("LOANS")
public class Loan {
    @Id
    private BigInteger id;
    private String loanNo;
    @NotNull
    @Length(min = 10, max = 10)
    private String customerAcctNo;
    private Date disbursementDate;
    private BigDecimal outstandingAmount;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanId) {
        this.loanNo = loanId;
    }

    public String getCustomerAcctNo() {
        return customerAcctNo;
    }

    public void setCustomerAcctNo(String customerAcctNo) {
        this.customerAcctNo = customerAcctNo;
    }

    public Date getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(Date disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public BigDecimal getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(BigDecimal outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }
}
