package com.example.demo.service;

import com.example.demo.dataAccess.User;
import com.example.demo.dataAccess.UserRepository;
import com.example.demo.response.ResponseForAuth;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService{

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseForAuth login(String id, String password){
        Optional<User> optionalUser = userRepository.findAllById(id);
        if(optionalUser.isEmpty()){
            return ResponseForAuth.newFailure("This id doesn't exist", 404);
        }
        else{
            User user = optionalUser.get();
            String rightPassword = user.getPassword();
            if(!password.equals(rightPassword)){
                return ResponseForAuth.newFailure("Password doesn't match", 400);
            }
            else{
                return ResponseForAuth.newSuccess();
            }
        }
    }

    @Override
    @Transactional
    public ResponseForAuth register(String id, String name, String password, int age, String e_mail){
        Optional<User> findById = userRepository.findAllById(id);
        if(findById.isPresent()) return ResponseForAuth.newFailure("This id has already been registered", 400);
        Optional<User> findByName = userRepository.findAllByName(name);
        if(findByName.isPresent()) return ResponseForAuth.newFailure("This name has been registered", 400);
        if(id.length() < 10) return ResponseForAuth.newFailure("id length must be no less than 10", 400);
        if(!id.matches(".*\\d.*") || !id.matches(".*[a-zA-Z].*"))
            return ResponseForAuth.newFailure("id must contain both letters and numbers", 400);
        if(password.length() < 10) return ResponseForAuth.newFailure("password length must be no less than 10", 400);
        if(!password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*"))
            return ResponseForAuth.newFailure("password must contain both letters and numbers",400);
//        User user = new User();
//        user.setId(id);
//        user.setName(name);
//        user.setPassword(password);
//        user.setAge(age);
//        user.setE_mail(e_mail);
        userRepository.addUser(id, name, password, age, e_mail);
        return ResponseForAuth.newSuccess();
    }
}
