package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.RES.Response2;
import com.example.demo.dataAccess.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/update")
    public Response2<?> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

}
