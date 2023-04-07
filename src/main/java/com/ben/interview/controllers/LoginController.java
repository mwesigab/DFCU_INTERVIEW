package com.ben.interview.controllers;

import com.ben.interview.helpers.GenericResponse;
import com.ben.interview.models.User;
import com.ben.interview.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public GenericResponse login(@RequestBody User user){
        List<User> users = null;
        try {
            users=loginService.login(user.getUsername(),user.getPassword());
            if(users.size()==0) return new GenericResponse(HttpStatus.NOT_FOUND.value(), "The user with the specified credentials does not exist.");
           return new GenericResponse(HttpStatus.OK.value(), "Success", users.get(0));
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    @PostMapping("/logout")
    @CrossOrigin(origins = "http://localhost:3000")
    public GenericResponse logout(@RequestBody User user){
        Integer response;
        try {
            response= loginService.logout(user.getUsername(), user.getPassword());
            return new GenericResponse(HttpStatus.OK.value(), "Success", response);
        }catch (Exception e){
            e.printStackTrace();
            return new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}
