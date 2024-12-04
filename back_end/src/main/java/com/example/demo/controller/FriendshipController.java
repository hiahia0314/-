package com.example.demo.controller;

import com.example.demo.dataAccess.Friendships;
import com.example.demo.service.FriendshipsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendshipController {

    @Autowired
    private FriendshipsServiceImp friendshipsServiceImp;

    @PostMapping("/add")
    public Long addFriendships(@RequestParam long user1, @RequestParam long user2) {
        return friendshipsServiceImp.addFriendship(user1, user2);
    }

    @GetMapping("/friends")
    public List<Friendships> getFriendships(@RequestParam long userId) {
        return friendshipsServiceImp.getFriendshipsById(userId);
    }
}
