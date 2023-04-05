package com.ben.interview.controllers;

import com.ben.interview.helpers.GenericResponse;
import com.ben.interview.models.Customer;
import com.ben.interview.services.CustomersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomersController {

    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping("/customers")
    public GenericResponse get() throws Exception{
        Iterable<Customer> customers = null;
        try {
            customers = customersService.get();
            return new GenericResponse(HttpStatus.OK.value(), "Success",customers);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/customers/{customerAcctNo}")
    public GenericResponse get(@PathVariable String customerAcctNo) throws Exception{
        List<Customer> customers = null;
        try {
           customers = customersService.get(customerAcctNo);
           if(customers.size()==0) return new GenericResponse(HttpStatus.NOT_FOUND.value(), "Sorry, this account number is invalid.");
           return new GenericResponse(HttpStatus.OK.value(), "Success",customers);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
