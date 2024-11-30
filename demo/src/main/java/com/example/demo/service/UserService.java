package com.example.demo.service;

import com.example.demo.DTO.UserDTO;

public interface UserService {
    public UserDTO getFriendById(long id);

    Long addNewFriend(UserDTO UserDTO);

    void deleteFriendById(long id);

    UserDTO updateFriendById(long id, String name, String age);
}
