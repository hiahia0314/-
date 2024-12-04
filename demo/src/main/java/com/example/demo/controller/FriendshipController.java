package com.example.demo.controller;

import com.example.demo.dataAccess.Friendships;
import com.example.demo.response.ResponseForFriendships;
import com.example.demo.service.FriendshipsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FriendshipController {

    @Autowired
    private FriendshipsServiceImp friendshipsServiceImp;

    @PostMapping("/addFriend")
    public ResponseEntity<Map<String, Object>> addFriendships(@RequestBody Friendships friendships) {
        Map<String, Object> response = new HashMap<>();
        ResponseForFriendships responseForFriendships = friendshipsServiceImp.addFriend(friendships.getApplicant(), friendships.getReceiver(), friendships.getTime());
        response.put("isSuccess", responseForFriendships.getIsSuccess());
        response.put("msg", responseForFriendships.getMsg());
        return ResponseEntity.status(0).body(response);
    }

    @GetMapping("/friends")
    public List<Friendships> getFriendships(@RequestParam long userId) {
        return friendshipsServiceImp.getFriendshipsById(userId);
    }
}
