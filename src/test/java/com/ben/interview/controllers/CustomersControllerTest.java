package com.ben.interview.controllers;

import com.ben.interview.models.Customer;
import com.ben.interview.services.CustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomersController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CustomersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Iterable<Customer> customers;
    @MockBean
    private CustomersService customersService;
    private Customer customer;
    @BeforeEach
    public void init(){
        customer = new Customer();
        customer.setId(BigInteger.valueOf(1));
        customer.setFirstName("Ben");
        customer.setLastName("Mwesiga");
        customer.setCustomerAcctNo("3204205610");
        customer.setStatus("ACTIVE");
        List<Customer> list = new ArrayList<>();
        list.add(customer);
        customers= list;
    }
    @Test
    void testGetCustomersList() throws Exception{
        when(customersService.get()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCustomerByAccountNumber() throws Exception{
        when(customersService.get(customer.getCustomerAcctNo())).thenReturn((List<Customer>) customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk());
    }
}