package com.ben.interview.services;

import com.ben.interview.models.Customer;
import com.ben.interview.repositories.CustomersRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CustomersService {


    private final CustomersRepository customersRepository;
    private final JdbcTemplate jdbcTemplate;


    public CustomersService(CustomersRepository customersRepository, JdbcTemplate jdbcTemplate) {
        this.customersRepository = customersRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterable<Customer> get(){
        return customersRepository.findAll();
    }

    public List<Customer> get(String customerAcctNo){
        return jdbcTemplate.query("SELECT * FROM CUSTOMERS WHERE CUSTOMER_ACCT_NO='"+customerAcctNo+"'", (rs, rowNum) -> {
            Customer customer = new Customer();
            customer.setId(BigInteger.valueOf(rs.getInt("id")));
            customer.setCustomerAcctNo(rs.getString("customer_acct_no"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setStatus(rs.getString("status"));
            return customer;
        });
    }
}
