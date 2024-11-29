package com.example.demo.service;


import com.example.demo.dataAccess.Friendships;

import java.util.List;

public interface FriendshipsService {
    Long addFriendship(Long userId, Long friendId);
    List<Friendships> getFriendshipsById(Long userId);

}
