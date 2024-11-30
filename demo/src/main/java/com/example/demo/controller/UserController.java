package com.example.demo.controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Response;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/friend/{id}")
    public Response<UserDTO> getFriendById(@PathVariable long id) {

        return Response.newSuccess(userService.getFriendById(id));
    }
    @GetMapping("/he")
    public String getHe() {
        return "he";
    }


    @PostMapping("/friend")
    public Response<Long> addNewFriend(@RequestBody UserDTO UserDTO) {
        return Response.newSuccess(userService.addNewFriend(UserDTO));
    }

    @DeleteMapping("/friend/{id}")
    public void deleteFriendById(@PathVariable long id) {
        userService.deleteFriendById(id);
    }

    @PutMapping("/friend/{id}")
    public Response<UserDTO> updateFriendById(@PathVariable long id, @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String age) {

        return Response.newSuccess(userService.updateFriendById(id,name,age));

    }
}
