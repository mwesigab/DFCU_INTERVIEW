package com.ben.interview.controllers;

import com.ben.interview.models.RequestLog;
import com.ben.interview.services.RequestLoggerService;
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

@WebMvcTest(controllers = RequestLoggerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class RequestLoggerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Iterable<RequestLog> requestLogs;
    @MockBean
    private RequestLoggerService loggerService;
    private RequestLog log;
    @BeforeEach
    public void init(){
        log = new RequestLog();
        log.setId(BigInteger.valueOf(1));
        log.setEndpoint("/logs");
        log.setDetails("/Test Log Details");
        log.setStatus("FAILED");

        List<RequestLog> list = new ArrayList<>();
        list.add(log);
        requestLogs= list;
    }
    @Test
    void testGetRequestLogsList() throws Exception{
        when(loggerService.getLogs()).thenReturn(requestLogs);

        mockMvc.perform(get("/logs"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNumberOfLogs() throws Exception{
       when(loggerService.get(log.getStatus())).thenReturn(1);

        mockMvc.perform(get("/logger/FAILED"))
                .andExpect(status().isOk());
    }
}