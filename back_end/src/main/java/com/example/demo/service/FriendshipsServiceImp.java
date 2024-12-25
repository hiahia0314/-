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
        if(friendships_o.isEmpty()) friendships_o = FSR.findByApplicantAndReceiver(receiverId, applicantId);

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
        Optional<Friendships> friendships_o1 = FSR.findByApplicantAndReceiver(applicantId, receiverId);
        Optional<Friendships> friendships_o2 = FSR.findByApplicantAndReceiver(receiverId, applicantId);
        if(friendships_o1.isEmpty() && friendships_o2.isEmpty()){
            return Response2.newFailure("This id doesn't exist", null);
        }else {
            if(friendships_o1.isPresent()){
                FSR.deleteByApplicantAndReceiver(applicantId, receiverId);
            }else {
                FSR.deleteByApplicantAndReceiver( receiverId,applicantId);
            }


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
            if(FriendId.isEmpty()){
                return Response2.newSuccess(friendDTOList);
            }
            FriendDTO friendDTO = new FriendDTO();
            friendDTO.setAccount(FriendId);
            friendDTO.setName(UR.findById(FriendId).get().getName());
            friendDTO.setAddTime(friendships.getDate());
            friendDTOList.add(friendDTO);
        }
        return Response2.newSuccess(friendDTOList);
    }


    public Response2<?> getFriendRequests(String UseId){
        List<Friendships> FriendshipsList=FSR.findByReceiver(UseId);
        List<FriendDTO> FriendDTOList = new ArrayList<>();

        for (Friendships friendships : FriendshipsList) {//获取申请者的信息

            if(!friendships.getStatus().equals("accepted")){
                FriendDTO FriendDTO = new FriendDTO();
                FriendDTO.setAccount(friendships.getApplicant());
                FriendDTO.setName(UR.findById(friendships.getApplicant()).get().getName());
                FriendDTO.setAddTime(friendships.getDate());
                FriendDTOList.add(FriendDTO);
            }

        }
        return Response2.newSuccess(FriendDTOList);
    }


    public Response2<?> handleApply(String userId, String applicantId, String isAccept){
        Optional<Friendships> friendships_o = FSR.findByApplicantAndReceiver(applicantId, userId);
        if(friendships_o.isEmpty()) return Response2.newFailure("This friendship doesn't exist", null);
        Friendships friendships = friendships_o.get();
        if(!friendships.getStatus().equals("pending")) return Response2.newFailure("This request has been" + isAccept + "ed", null);
        if(isAccept.equals("accept")){
            friendships.setStatus("accepted");
            FSR.save(friendships);
            return Response2.newSuccess(null);
        }
        else{
            friendships.setStatus("rejected");
            FSR.save(friendships);
            return Response2.newSuccess(null);
        }
    }

}