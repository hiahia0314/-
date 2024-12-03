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
    @Override
    @Transactional//回滚
    public UserDTO updateUserByUserId(String id, String e_mail, int age,String name) {


        return null;
    }
}
