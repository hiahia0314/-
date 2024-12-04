package com.example.demo.service;

import com.example.demo.RES.Response2;
import com.example.demo.RES.Response2Auth;
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
    public Response2 login(String id, String password){
        Optional<User> optionalUser = userRepository.findAllById(id);
        if(optionalUser.isEmpty()){
            return Response2Auth.newFailure("This id doesn't exist");
        }
        else{
            User user = optionalUser.get();
            String rightPassword = user.getPassword();
            if(!password.equals(rightPassword)){
                return Response2Auth.newFailure("Password doesn't match");
            }
            else{
                return Response2Auth.newSuccess("successfully");
            }
        }
    }

    @Override
    @Transactional
    public Response2 register(String id, String name, String password, int age, String e_mail){
        Optional<User> findById = userRepository.findAllById(id);
        if(findById.isPresent()) return Response2Auth.newFailure("This id has already been registered");
        Optional<User> findByName = userRepository.findAllByName(name);
        if(findByName.isPresent()) return Response2Auth.newFailure("This name has been registered");
        if(id.length() < 10) return Response2Auth.newFailure("id length must be no less than 10");
        if(!id.matches(".*\\d.*") || !id.matches(".*[a-zA-Z].*"))
            return Response2Auth.newFailure("id must contain both letters and numbers");
        if(password.length() < 10) return Response2Auth.newFailure("password length must be no less than 10");
        if(!password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*"))
            return Response2Auth.newFailure("password must contain both letters and numbers");
//        User user = new User();
//        user.setId(id);
//        user.setName(name);
//        user.setPassword(password);
//        user.setAge(age);
//        user.setE_mail(e_mail);
        userRepository.addUser(id, name, password, age, e_mail);
        return Response2Auth.newSuccess("successfully");
    }
}
