package com.ben.interview.services;

import com.ben.interview.models.Loan;
import com.ben.interview.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final JdbcTemplate jdbcTemplate;

    public LoginService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> login(String username, String password){
        return jdbcTemplate.query("SELECT * FROM USERS WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'", (rs, rowNum) -> {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setToken(rs.getString("token"));
            user.setStatus(rs.getString("status"));
            return user;
        });
    }
}
