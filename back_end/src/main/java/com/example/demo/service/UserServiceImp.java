package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.RES.Response2;
import com.example.demo.converter.UserConverter;
import com.example.demo.dataAccess.User;
import com.example.demo.dataAccess.EventRepository;
import com.example.demo.dataAccess.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public Response2<?> updateUser(User user){
        Optional<User> userInDB = userRepository.findById(user.getId());
        if(userInDB.isPresent()){
            userInDB.get().setName(user.getName());
            userInDB.get().setAge(user.getAge());
            userInDB.get().setE_mail(user.getE_mail());
            userInDB.get().setPassword(user.getPassword());
            userRepository.save(userInDB.get());
            return Response2.newSuccess(userInDB);
        }else {
            return Response2.newFailure("this id doesn't exist",null);
        }
    }
}
