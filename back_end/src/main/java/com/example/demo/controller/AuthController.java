package com.example.demo.controller;

import com.example.demo.RES.Response2;
import com.example.demo.RES.Response2Auth;
import com.example.demo.dataAccess.User;
import com.example.demo.response.ResponseForAuth;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Response2 login(@RequestBody User user) {
        return authService.login(user.getId(), user.getPassword());
    }

    @PostMapping("/register")
    public Response2 register(@RequestBody User user) {
        return authService.register(user.getId(), user.getName(), user.getPassword(), user.getAge(), user.getE_mail());
    }

}
