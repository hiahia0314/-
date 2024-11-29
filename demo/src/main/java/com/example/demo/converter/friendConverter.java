package com.example.demo.converter;

import com.example.demo.DTO.friendDTO;
import com.example.demo.dataAccess.Friend;

public class friendConverter {
    public static friendDTO convertFriend(Friend friend) {
        friendDTO friendDTO = new friendDTO();
        friendDTO.setId(friend.getId());
        friendDTO.setName(friend.getName());
        friendDTO.setAge(friend.getAge());
        return friendDTO;
    }

    public static Friend convertFriend(friendDTO friendDTO) {
        Friend friend = new Friend();
        friend.setId(friendDTO.getId());
        friend.setName(friendDTO.getName());
        friend.setAge(friendDTO.getAge());
        return friend;
    }
}
