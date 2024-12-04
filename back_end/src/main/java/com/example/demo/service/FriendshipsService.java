package com.example.demo.service;


import com.example.demo.dataAccess.Friendships;

import java.util.List;

public interface FriendshipsService {
    String addFriendship(String userId, String friendId);
    List<Friendships> getFriendshipsById(Long userId);

}
