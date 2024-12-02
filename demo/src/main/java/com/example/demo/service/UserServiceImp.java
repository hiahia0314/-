package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.converter.UserConverter;
import com.example.demo.dataAccess.User;
import com.example.demo.dataAccess.EventRepository;
import com.example.demo.dataAccess.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;
    @Override
    public UserDTO getFriendById(String id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return UserConverter.convertFriend(user);
    }

    public String addNewFriend(UserDTO UserDTO) {
        List<User> userList = userRepository.findByName(UserDTO.getName());//JPA已经帮我实现了
        if(!CollectionUtils.isEmpty(userList)){
            //throw new IllegalStateException("name: "+friendDTO.getName()+" already exists");
            return null;
        }
        User user = userRepository.save(UserConverter.convertFriend(UserDTO));
        return user.getId();
    }

    @Override
    public void deleteFriendById(String id) {
        userRepository.findById(id).orElseThrow( ()-> new IllegalArgumentException("id :" + id + " not found"));
        userRepository.deleteById(id);

        if(eventRepository.existsById(id)){
            eventRepository.deleteAllByUser(id);
        }
    }
    @Override
    @Transactional//回滚
    public UserDTO updateFriendById(String id, String name, String age) {


        User userInDB = userRepository.findById(id).orElseThrow(RuntimeException::new);
        if(name!=null&&!name.isEmpty()){

            userInDB.setName(name);
        }
        if(age!=null&&!age.isEmpty()){
            userInDB.setAge(Integer.parseInt(age));
        }

        User user = userRepository.save(userInDB);
        return UserConverter.convertFriend(user);
    }
}
