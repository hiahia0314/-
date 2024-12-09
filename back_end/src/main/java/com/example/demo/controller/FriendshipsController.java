package com.example.demo.controller;

import com.example.demo.RES.Response2;
import com.example.demo.dataAccess.Friendships;
import com.example.demo.service.FriendshipsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendshipsController {

    @Autowired
    private FriendshipsServiceImp friendshipsServiceImp;

    @PostMapping("/addFriend")
    public Response2<?> addFriendships(@RequestBody Friendships friendships) {
        return friendshipsServiceImp.addFriend(friendships.getApplicant(), friendships.getReceiver(), friendships.getDate());
    }

    @DeleteMapping("deleteFriend")
    public Response2<?> deleteFriendships(@RequestBody Friendships friendships) {
        return friendshipsServiceImp.deleteFriend(friendships.getApplicant(), friendships.getReceiver());
    }

    @GetMapping("getFriend")
    public Response2<?> getFriendships(@RequestBody Friendships friendships) {
        return friendshipsServiceImp.getFriends(friendships.getApplicant());
    }

//    @GetMapping("/friends")
//    public List<Friendships> getFriendships(@RequestParam long userId) {
//        return friendshipsServiceImp.getFriendshipsById(userId);
//    }
}

