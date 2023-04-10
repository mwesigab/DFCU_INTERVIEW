package com.ben.interview.controllers;

import com.ben.interview.models.Customer;
import com.ben.interview.models.Loan;
import com.ben.interview.services.CustomersService;
import com.ben.interview.services.LoansService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LoansController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class LoansControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Iterable<Loan> loans;
    @MockBean
    private LoansService loansService;
    private Loan loan;
    @BeforeEach
    public void init(){
        loan = new Loan();
        loan.setId(BigInteger.valueOf(1));
        loan.setLoanNo("LN4203200");
        loan.setOutstandingAmount(BigDecimal.valueOf(5000000));
        loan.setDisbursementDate(new Date());
        List<Loan> list = new ArrayList<>();
        list.add(loan);
        loans= list;
    }
    @Test
    void testGetCustomersList() throws Exception{
        when(loansService.get()).thenReturn(loans);

        mockMvc.perform(get("/loans"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCustomerByAccountNumber() throws Exception{
       /* when(loans.get(customer.getCustomerAcctNo())).thenReturn((List<Customer>) customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk());*/
    }
}