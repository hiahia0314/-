package com.example.demo.service;

import com.example.demo.DTO.FriendDTO;
import com.example.demo.RES.Response2;
import com.example.demo.dataAccess.Friendships;
import com.example.demo.dataAccess.FriendshipsRepository;
import com.example.demo.dataAccess.User;
import com.example.demo.dataAccess.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendshipsServiceImp implements FriendshipsService {

    @Autowired
    FriendshipsRepository FSR;
    @Autowired
    UserRepository UR;

    @Override
    public Response2<?> addFriend(String applicantId, String receiverId, LocalDate date){
        Optional<User> receiver = UR.findById(receiverId);
        if(receiver.isEmpty()) return Response2.newFailure("This id doesn't exist", null);
        Optional<Friendships> friendships_o = FSR.findByApplicantAndReceiver(applicantId, receiverId);
        if(friendships_o.isPresent()){
            String status = friendships_o.get().getStatus();
            switch (status) {
                case "pending" -> {
                    return Response2.newFailure("This request is still pending", null);
                }
                case "accepted" -> {
                    return Response2.newFailure("This request has been already accepted", null);
                }
                case "rejected" -> {
                    Friendships friendships = friendships_o.get();
                    friendships.setStatus("pending");
                    friendships.setDate(date);
                    FSR.save(friendships);
                    return Response2.newSuccess(null);
                }
            }
        }
        Friendships friendships = new Friendships();
        friendships.setApplicant(applicantId);
        friendships.setReceiver(receiverId);
        friendships.setStatus("pending");
        friendships.setDate(date);
        FSR.save(friendships);
        return Response2.newSuccess(null);
    }

    @Override
    public Response2<?> deleteFriend(String applicantId, String receiverId){
        Optional<Friendships> friendships_o = FSR.findByApplicantAndReceiver(applicantId, receiverId);
        if(friendships_o.isEmpty()){
            return Response2.newFailure("This id doesn't exist", null);
        }else {
            FSR.deleteByApplicantAndReceiver(applicantId, receiverId);
            return Response2.newSuccess(null);
        }
    }
    public Response2<?> getFriendsList(String UserId){
        //
        List<Friendships> list=FSR.findByApplicantOrReceiver(UserId);
        String FriendId="";

        List<FriendDTO> friendDTOList = new ArrayList<>();
        for (Friendships friendships : list) {
            if(friendships.getReceiver().equals(UserId)){//此时好友是applicant
                FriendId=friendships.getApplicant();
            }else if(friendships.getApplicant().equals(UserId)){//此时好友是receiver
                FriendId=friendships.getReceiver();
            }
            FriendDTO friendDTO = new FriendDTO();
            friendDTO.setId(FriendId);
            friendDTO.setName(UR.findById(UserId).get().getName());
            friendDTO.setDate(friendships.getDate());
            friendDTOList.add(friendDTO);
        }



        return Response2.newSuccess(friendDTOList);
    }

}
