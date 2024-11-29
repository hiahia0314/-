package com.example.demo.service;

import com.example.demo.DTO.friendDTO;

public interface FriendService {
    public friendDTO getFriendById(long id);

    Long addNewFriend(friendDTO friendDTO);

    void deleteFriendById(long id);

    friendDTO updateFriendById(long id, String name, String age);
}
