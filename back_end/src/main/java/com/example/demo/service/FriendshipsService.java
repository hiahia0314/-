package com.example.demo.service;


import com.example.demo.RES.Response2;
import com.example.demo.dataAccess.Friendships;

import java.time.LocalDate;
import java.util.List;

public interface FriendshipsService {
//    String addFriendship(String userId, String friendId);
    public Response2<?> addFriend(String applicantId, String receiverId, LocalDate time);
//    List<Friendships> getFriendshipsById(Long userId);

}
