package com.example.demo.service;

import com.example.demo.dataAccess.Friendships;
import com.example.demo.dataAccess.FriendshipsRepository;
import com.example.demo.dataAccess.User;
import com.example.demo.dataAccess.UserRepository;
import com.example.demo.response.ResponseForFriendships;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FriendshipsServiceImp implements FriendshipsService {

    @Autowired
    FriendshipsRepository FSR;
    UserRepository UR;

    public String addFriendship(String userId, String friendId){
        if(FSR.existsByUserAndFriend(userId, friendId)){
            return null;
        }else {
            Friendships friendships = new Friendships();
            friendships.setReceiver(friendId);
            friendships.setApplicant(userId);
            Friendships a= FSR.save(friendships);
            return a.getReceiver();
        }
    }

    public ResponseForFriendships addFriend(String applicantId, String receiverId, LocalDate time){
        Optional<User> receiver = UR.findById(receiverId);
        if(receiver.isEmpty()) return ResponseForFriendships.newFailure("This id doesn't exist");
        Optional<Friendships> friendships_o = FSR.findByApplicantAndReceiver(applicantId, receiverId);
        if(friendships_o.isPresent()){
            String status = friendships_o.get().getStatus();
            if(status.equals("pending")) return ResponseForFriendships.newFailure("This request is still pending");
        }
        Friendships friendships = new Friendships();
        friendships.setApplicant(applicantId);
        friendships.setReceiver(receiverId);
        friendships.setStatus("pending");
        FSR.save(friendships);
        return ResponseForFriendships.newSuccess();
    }

    public List<Friendships> getFriendshipsById(Long userId){
        return FSR.findByUserOrFriend(userId,userId);
    }

}
