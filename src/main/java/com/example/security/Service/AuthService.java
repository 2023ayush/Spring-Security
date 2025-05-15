package com.example.security.Service;

import com.example.security.ApiResponse;
import com.example.security.Dto.UserDto;
import com.example.security.Entity.User;
import com.example.security.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse<String> register(UserDto dto){
        if(userRepository.existsByUsername(dto.getUsername())){
            ApiResponse<String> response = new ApiResponse<>();
            response.setMessage("Resgistration Failed");
            response.setStatus(500);
            response.setData("user with this username already exits");
            return response;
        }
        if(userRepository.existsByEmail((dto.getEmail()))){
            ApiResponse<String> response = new ApiResponse<>();
            response.setMessage("Registration Failed");
            response.setStatus(500);
            response.setData("user with this email already exits");
            return response;
        }
           User user = new User();
        BeanUtils.copyProperties(dto,user);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(user);
        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Registration Done");
        response.setStatus(201);
        response.setData("User is registered");

        return response;


    }
}
