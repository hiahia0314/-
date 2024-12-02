package com.example.demo.controller;

import com.example.demo.dataAccess.User;
import com.example.demo.response.ResponseForAuth;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseForAuth login(@RequestBody User user){
        return authService.login(user.getId(), user.getPassword());
    }

    @PostMapping("/register")
    public ResponseForAuth register(@RequestBody User user){
        return authService.register(user.getId(), user.getName(), user.getPassword(), user.getAge(), user.getE_mail());
    }
}
