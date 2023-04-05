package com.ben.interview.controllers;

import com.ben.interview.helpers.GenericResponse;
import com.ben.interview.models.Customer;
import com.ben.interview.models.Loan;
import com.ben.interview.services.CustomersService;
import com.ben.interview.services.LoansService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
public class LoansController {

    private final LoansService loansService;
    private final CustomersService customersService;

    public LoansController(LoansService loansService, CustomersService customersService) {
        this.loansService = loansService;
        this.customersService = customersService;
    }

    /*
    * Gets a list of loans
    * */
    @GetMapping("/loans")
    public GenericResponse get() throws Exception{
        Iterable<Loan> loans;
        try {
            loans=loansService.get();
            return new GenericResponse(HttpStatus.OK.value(), "Success",loans);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /*
    * Gets the status of a customer's loans
    * */
    @GetMapping("/loans/{customerAcctNo}")
    public GenericResponse get(@PathVariable String customerAcctNo) throws Exception{
        try {
            if(customerAcctNo.length()!=10) return new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Account Number Length.");
            if(!checkAcctNumber(customerAcctNo)) return new GenericResponse(HttpStatus.NOT_FOUND.value(), "Account Number Does Not Exist.");

            List<Loan> loans = loansService.get(customerAcctNo);
            if(loans.size()==0) return new GenericResponse(HttpStatus.NOT_FOUND.value(),"No Loan Found.");
            return new GenericResponse(HttpStatus.OK.value(), "Success",loans);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }


    /*
    * Checks if the account number exists in the database
    * */
    private Boolean checkAcctNumber(String customerAcctNumber) throws Exception{
        try {
            List<Customer> customer = customersService.get(customerAcctNumber);
            return customer.size() != 0;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
