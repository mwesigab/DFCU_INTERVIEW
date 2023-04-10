package com.ben.interview.controllers;

import com.ben.interview.helpers.GenericResponse;
import com.ben.interview.models.Customer;
import com.ben.interview.models.Loan;
import com.ben.interview.services.CustomersService;
import com.ben.interview.services.LoansService;
import com.ben.interview.services.RequestLoggerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
@RestController
public class LoansController {

    private final LoansService loansService;
    private final CustomersService customersService;
    private final RequestLoggerService loggerService;

    public LoansController(LoansService loansService, CustomersService customersService, RequestLoggerService loggerService) {
        this.loansService = loansService;
        this.customersService = customersService;
        this.loggerService = loggerService;
    }

    @ApiOperation(value = "Get a list of loans available.", notes = "Returns a list of loans.")
    @GetMapping("/loans")
    public GenericResponse get() throws Exception{
        Iterable<Loan> loans;
        try {
            loans= loansService.get();
            return new GenericResponse(HttpStatus.OK.value(), "Success",loans);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @ApiOperation(value = "Get a customer's loan status by account number", notes = "Returns one or more loans as per the account number.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 400, message = "Invalid Account Number Length/No Loan Found."),
            @ApiResponse(code = 404, message = "Not found - Account Number Does Not Exist.")
    })
    @GetMapping("/loans/{customerAcctNo}")
    public GenericResponse get(@PathVariable String customerAcctNo) throws Exception{
        try {
            if(customerAcctNo.length()!=10) {
                loggerService.logger("/loans/"+customerAcctNo,"Invalid Account Number Length.","FAILED");
                return new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Account Number Length.");
            }
            if(!checkAcctNumber(customerAcctNo)) {
                loggerService.logger("/loans/"+customerAcctNo,"Account Number Does Not Exist.","FAILED");
                return new GenericResponse(HttpStatus.NOT_FOUND.value(), "Account Number Does Not Exist.");
            }

            List<Loan> loans = loansService.get(customerAcctNo);
            if(loans.size()==0) {
                loggerService.logger("/loans/"+customerAcctNo,"No Loan Found.","NEGATIVE");
                return new GenericResponse(HttpStatus.NOT_FOUND.value(), "No Loan Found.");
            }
            loggerService.logger("/loans/"+customerAcctNo,"Success","POSITIVE");
            return new GenericResponse(HttpStatus.OK.value(), "Success",loans);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /*
    * Checks the validity of the account number
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
