package com.example.demo.controller;

import com.example.demo.RES.Response2;
import com.example.demo.dataAccess.Friendships;
import com.example.demo.service.FriendshipsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendshipController {

    @Autowired
    private FriendshipsServiceImp friendshipsServiceImp;

    @PostMapping("/addFriend")
    public Response2<?> addFriendships(@RequestBody Friendships friendships) {
        return friendshipsServiceImp.addFriend(friendships.getApplicant(), friendships.getReceiver(), friendships.getDate());
    }

//    @GetMapping("/friends")
//    public List<Friendships> getFriendships(@RequestParam long userId) {
//        return friendshipsServiceImp.getFriendshipsById(userId);
//    }
}

