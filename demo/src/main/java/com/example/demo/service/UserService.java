package com.example.demo.service;

import com.example.demo.DTO.UserDTO;

public interface UserService {
    public UserDTO getFriendById(String id);

    String addNewFriend(UserDTO UserDTO);

    void deleteFriendById(String id);

    UserDTO updateFriendById(String id, String name, String age);
}
