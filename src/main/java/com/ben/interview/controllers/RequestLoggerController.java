package com.ben.interview.controllers;

import com.ben.interview.helpers.GenericResponse;
import com.ben.interview.models.RequestLog;
import com.ben.interview.services.RequestLoggerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestLoggerController{

    private final RequestLoggerService requestLoggerService;

    public RequestLoggerController(RequestLoggerService requestLoggerService) {
        this.requestLoggerService = requestLoggerService;
    }

    @ApiOperation(value = "Get a list of request logs available.", notes = "Returns a list of request logs.")
    @GetMapping("logs")
    public GenericResponse getLogs(){
        try {
            Iterable<RequestLog> list = requestLoggerService.getLogs();
            return new GenericResponse(HttpStatus.OK.value(), "Success",list);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @ApiOperation(value = "Get number of logs by status.", notes = "Returns the number of logs per status. Examples of statuses include FAILED (Failed Validations)," +
            "POSITIVE (Number of positive requests/outstanding loans) and NEGATIVE (Number of negative requests/ no outstanding loans.)")
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
