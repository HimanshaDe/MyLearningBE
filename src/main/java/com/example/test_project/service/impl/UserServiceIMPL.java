package com.example.test_project.service.impl;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.UserRequestDTO;
import com.example.test_project.entity.User;
import com.example.test_project.repository.UserRepository;
import com.example.test_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        ResponseDTO responseDTO = new ResponseDTO();

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());
        userRepository.save(user);

        responseDTO.setData(user);
        responseDTO.setStatus(HttpStatus.CREATED.value());
        responseDTO.setTimestamp(LocalDateTime.now());
        return responseDTO;
    }
}
