package com.example.demo.service;

import com.example.demo.response.ResponseForAuth;

public interface AuthService {

    ResponseForAuth login(String id, String password);

    ResponseForAuth register(String id, String name, String password, int age, String email);
}
