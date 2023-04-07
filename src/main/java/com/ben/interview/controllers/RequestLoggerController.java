package com.ben.interview.controllers;

import com.ben.interview.helpers.GenericResponse;
import com.ben.interview.models.RequestLog;
import com.ben.interview.services.RequestLoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RequestLoggerController{

    private final RequestLoggerService requestLoggerService;

    public RequestLoggerController(RequestLoggerService requestLoggerService) {
        this.requestLoggerService = requestLoggerService;
    }

    @GetMapping("logs")
    @CrossOrigin(origins = "http://localhost:3000")
    public GenericResponse getLogs(){
        try {
            Iterable<RequestLog> list = requestLoggerService.getLogs();
            return new GenericResponse(HttpStatus.OK.value(), "Success",list);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @GetMapping("/logger")
    public GenericResponse get(){
        try{
            Integer response = requestLoggerService.get();
            return new GenericResponse(HttpStatus.OK.value(), "Success",response);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/logger/{status}")
    public GenericResponse Integer(@PathVariable  String status){
        try {
            Integer response = requestLoggerService.get(status);
            return new GenericResponse(HttpStatus.OK.value(), "Success",response);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failure");
        }
    }
}
