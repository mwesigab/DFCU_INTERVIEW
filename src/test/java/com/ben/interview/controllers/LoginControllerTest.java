package com.ben.interview.controllers;

import com.ben.interview.models.User;
import com.ben.interview.services.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = LoginController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private LoginService loginService;
    private Iterable<User> users;
    private User user;

    @BeforeEach
    void init() {
        user = new User();
        user.setId(BigInteger.valueOf(1));
        user.setUsername("mwesiga@admin.com");
        user.setPassword("admin");
        user.setToken("Token");
        user.setLoggedIn(true);
        List<User> list = new ArrayList<>();
        list.add(user);
        users = list;
    }

    @Test
    void testLoginUser() throws Exception {
        given(loginService.login(user.getUsername(),user.getPassword())).willAnswer(invocation->invocation.getArgument(0));

        ResultActions response = mvc.perform(post("/login"));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testLogoutUser() {
    }
}