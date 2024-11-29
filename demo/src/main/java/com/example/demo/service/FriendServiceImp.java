package com.example.demo.service;

import com.example.demo.DTO.friendDTO;
import com.example.demo.converter.friendConverter;
import com.example.demo.dataAccess.Friend;
import com.example.demo.dataAccess.EventRepository;
import com.example.demo.dataAccess.FriendRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class FriendServiceImp implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private EventRepository eventRepository;
    @Override
    public friendDTO getFriendById(long id) {
        Friend friend = friendRepository.findById(id).orElseThrow(RuntimeException::new);
        return friendConverter.convertFriend(friend);
    }

    public Long addNewFriend(friendDTO friendDTO) {
        List<Friend> friendList= friendRepository.findByName(friendDTO.getName());//JPA已经帮我实现了
        if(!CollectionUtils.isEmpty(friendList)){
            //throw new IllegalStateException("name: "+friendDTO.getName()+" already exists");
            return (long) -1;
        }
        Friend friend= friendRepository.save(friendConverter.convertFriend(friendDTO));
        return friend.getId();
    }

    @Override
    public void deleteFriendById(long id) {
        friendRepository.findById(id).orElseThrow( ()-> new IllegalArgumentException("id :" + id + " not found"));
        friendRepository.deleteById(id);

        if(eventRepository.existsByUser(id)){
            eventRepository.deleteAllByUser(id);
        }
    }
    @Override
    @Transactional//回滚
    public friendDTO updateFriendById(long id, String name,String age) {


        Friend friendInDB = friendRepository.findById(id).orElseThrow(RuntimeException::new);
        if(name!=null&&!name.isEmpty()){

            friendInDB.setName(name);
        }
        if(age!=null&&!age.isEmpty()){
            friendInDB.setAge(Integer.parseInt(age));
        }

        Friend friend = friendRepository.save(friendInDB);
        return friendConverter.convertFriend(friend);
    }
}
