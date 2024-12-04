package com.example.demo.service;

import com.example.demo.RES.Response2;
import com.example.demo.RES.Response2Auth;


public interface AuthService {

    Response2 login(String id, String password);

    Response2 register(String id, String name, String password, int age, String email);
}
