package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.RES.Response2;
import com.example.demo.dataAccess.User;

public interface UserService {
    //UserDTO updateUserByUserId(String id, String e_mail, int age,String name);
    Response2<?> updateUser(User user);
}
