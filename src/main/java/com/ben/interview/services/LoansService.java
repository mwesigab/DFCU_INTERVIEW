package com.ben.interview.services;

import com.ben.interview.models.Loan;
import com.ben.interview.repositories.LoansRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class LoansService {

    private final LoansRepository loansRepository;
    private final JdbcTemplate jdbcTemplate;

    public LoansService(LoansRepository loansRepository, JdbcTemplate jdbcTemplate) {
        this.loansRepository = loansRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterable<Loan> get() {
        return loansRepository.findAll();
    }

    public List<Loan> get(String customerNo){
        return jdbcTemplate.query("SELECT * FROM LOANS WHERE CUSTOMER_ACCT_NO='"+customerNo+"'", (rs, rowNum) -> {
            Loan loan = new Loan();
            loan.setCustomerAcctNo(rs.getString("customer_acct_no"));
            loan.setLoanNo(rs.getString("loan_no"));
            loan.setDisbursementDate(rs.getDate("disbursement_date"));
            loan.setOutstandingAmount(rs.getBigDecimal("outstanding_amount"));
            return loan;
        });
    }
}
