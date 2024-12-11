package com.example.demo.controller;

import com.example.demo.RES.Response2;
import com.example.demo.dataAccess.Friendships;
import com.example.demo.service.FriendshipsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendshipsController {

    @Autowired
    private FriendshipsServiceImp friendshipsServiceImp;

    @PostMapping("/addFriend")
    public Response2<?> addFriendships(@RequestBody Friendships friendships) {
        return friendshipsServiceImp.addFriend(friendships.getApplicant(), friendships.getReceiver(), friendships.getDate());
    }

    @PostMapping("deleteFriend")
    public Response2<?> deleteFriendships(@RequestBody Friendships friendships) {
        return friendshipsServiceImp.deleteFriend(friendships.getApplicant(), friendships.getReceiver());
    }

    @GetMapping("getFriend/{UserId}")
    public Response2<?> getFriendR(@PathVariable String UserId) {
        List<Response2<?>> list = new ArrayList<>();

        Response2<?> Friendships = friendshipsServiceImp.getFriendsList(UserId);
        list.add(Friendships);
        Response2<?> FriendRequest = friendshipsServiceImp.getFriendRequests(UserId);
        list.add(FriendRequest);
        return Response2.newSuccess(list);

    }
//
//    @GetMapping("getFriend/{UserId}")
//    public Response2<?> getFriendships(@PathVariable String UserId) {
//        return friendshipsServiceImp.getFriendsList(UserId);
//    }
//
//    @GetMapping("getFriendRequest/{UserId}")
//    public Response2<?> getFriendRequest(@PathVariable String UserId) {
//        return friendshipsServiceImp.getFriendRequests(UserId);
//    }

    @PostMapping("/handleApply")
    public Response2<?> handleApply(@RequestBody Friendships friendships){
        return friendshipsServiceImp.handleApply(friendships.getReceiver(), friendships.getApplicant(), friendships.getStatus());
    }

}

