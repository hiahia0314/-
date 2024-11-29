package com.example.demo.controller;

import com.example.demo.DTO.friendDTO;
import com.example.demo.Response;
import com.example.demo.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendController {
    @Autowired
    private FriendService friendService;

    @GetMapping("/friend/{id}")
    public Response<friendDTO> getFriendById(@PathVariable long id) {

        return Response.newSuccess(friendService.getFriendById(id));
    }
    @GetMapping("/he")
    public String getHe() {
        return "he";
    }


    @PostMapping("/friend")
    public Response<Long> addNewFriend(@RequestBody friendDTO friendDTO) {
        return Response.newSuccess(friendService.addNewFriend(friendDTO));
    }

    @DeleteMapping("/friend/{id}")
    public void deleteFriendById(@PathVariable long id) {
        friendService.deleteFriendById(id);
    }

    @PutMapping("/friend/{id}")
    public Response<friendDTO> updateFriendById(@PathVariable long id,@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String age) {

        return Response.newSuccess(friendService.updateFriendById(id,name,age));

    }
}
