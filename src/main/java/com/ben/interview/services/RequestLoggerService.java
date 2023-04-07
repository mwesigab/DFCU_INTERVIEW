package com.ben.interview.services;

import com.ben.interview.models.RequestLog;
import com.ben.interview.repositories.RequestLoggerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestLoggerService {
    private final JdbcTemplate jdbcTemplate;
    private final RequestLoggerRepository requestLoggerRepository;

    public RequestLoggerService(JdbcTemplate jdbcTemplate, RequestLoggerRepository requestLoggerRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.requestLoggerRepository = requestLoggerRepository;
    }

    public Integer get(){
        return jdbcTemplate.queryForObject("SELECT COUNT(ID) FROM REQUEST_LOGS",Integer.class);
    }

    public Integer get(String status){
        return jdbcTemplate.queryForObject("SELECT COUNT(ID) FROM REQUEST_LOGS WHERE STATUS='"+status+"'",Integer.class);
    }

    public void logger(String endpoint, String details, String status){
        try {
            RequestLog requestLog = new RequestLog();
            requestLog.setEndpoint(endpoint);
            requestLog.setDetails(details);
            requestLog.setStatus(status);
            requestLoggerRepository.save(requestLog);
            System.out.println(requestLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Iterable<RequestLog> getLogs() {
        return requestLoggerRepository.findAll();
    }
}
