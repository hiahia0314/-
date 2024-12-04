package com.example.demo.service;

import com.example.demo.DTO.UserDTO;

public interface UserService {
    UserDTO updateUserByUserId(String id, String e_mail, int age,String name);
}
