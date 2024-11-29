package com.example.demo.service;

import com.example.demo.dataAccess.Friendships;
import com.example.demo.dataAccess.FriendshipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipsServiceImp implements FriendshipsService {

    @Autowired
    FriendshipsRepository FSR;

    public  Long addFriendship(Long userId, Long friendId){
        if(FSR.existsByUserAndFriend(userId, friendId)){
            return (long) -1;
        }else {
            Friendships friendships = new Friendships();
            friendships.setFriend(friendId);
            friendships.setUser(userId);
            Friendships a= FSR.save(friendships);
            return a.getId();
        }
    }

    public List<Friendships> getFriendshipsById(Long userId){
        return FSR.findByUserOrFriend(userId,userId);
    }

}
