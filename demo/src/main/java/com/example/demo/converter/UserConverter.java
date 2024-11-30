package com.example.demo.converter;

import com.example.demo.DTO.UserDTO;
import com.example.demo.dataAccess.User;

public class UserConverter {
    public static UserDTO convertFriend(User user) {
        UserDTO UserDTO = new UserDTO();
        UserDTO.setId(user.getId());
        UserDTO.setName(user.getName());
        UserDTO.setAge(user.getAge());
        return UserDTO;
    }

    public static User convertFriend(UserDTO UserDTO) {
        User user = new User();
        user.setId(UserDTO.getId());
        user.setName(UserDTO.getName());
        user.setAge(UserDTO.getAge());
        return user;
    }
}
