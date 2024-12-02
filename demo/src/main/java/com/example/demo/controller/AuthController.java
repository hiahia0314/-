package com.example.demo.controller;

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
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
        Map<String, Object> response = new HashMap<>();
        ResponseForAuth responseForAuth = authService.login(user.getId(), user.getPassword());
        response.put("isSuccess", responseForAuth.getIsSuccess());
        response.put("msg", responseForAuth.getMsg());
        return ResponseEntity.status(responseForAuth.getStatus()).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user){
        Map<String, Object> response = new HashMap<>();
        ResponseForAuth responseForAuth = authService.register(user.getId(), user.getName(), user.getPassword(), user.getAge(), user.getE_mail());
        response.put("isSuccess", responseForAuth.getIsSuccess());
        response.put("msg", responseForAuth.getMsg());
        return ResponseEntity.status(responseForAuth.getStatus()).body(response);
    }
}
