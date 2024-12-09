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

    @GetMapping("getFriend/{UserId}")
    public Response2<?> getFriendships(@PathVariable String UserId) {
        return friendshipsServiceImp.getFriendsList(UserId);
    }

    @PostMapping("/handleApply")
    public Response2<?> handleApply(@RequestBody Friendships friendships){
        return friendshipsServiceImp.handleApply(friendships.getReceiver(), friendships.getApplicant(), friendships.getStatus());
    }

}

