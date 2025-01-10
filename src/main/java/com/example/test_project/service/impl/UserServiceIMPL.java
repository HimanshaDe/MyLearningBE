package com.example.test_project.service.impl;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.UserRequestDTO;
import com.example.test_project.entity.User;
import com.example.test_project.repository.UserRepository;
import com.example.test_project.service.UserService;
import com.example.test_project.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseDTO createUser(UserRequestDTO userRequestDTO, BindingResult bindingResult) {

        User user = new User();
        ResponseDTO responseDTO = new ResponseDTO();

        if (bindingResult.hasErrors()) {
            ResponseDTO errorResponse = new ResponseDTO();
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setErrors(bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST).getBody();
        }
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());
        user.setPassword(userRequestDTO.getPassword());
        userRepository.save(user);

        ResponseDTO responseDTOnew = new ResponseDTO();
        ResponseUtil.handleCreateResponse(responseDTOnew,user,"User Created successfully.");
        return responseDTOnew;
    }

    @Override
    public ResponseDTO getUserList() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()){
            ResponseUtil.handleNotFoundResponse(responseDTO,"User list is empty");
        }else {
            ResponseUtil.handleOkResponse(responseDTO,userList,"User List retrieved successfully.");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getUserById(Integer userId) {
        ResponseDTO responseDTO = new ResponseDTO<>();
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            ResponseUtil.handleOkResponse(responseDTO, user, "User retrieved successfully");
        }
        else {
            ResponseUtil.handleNotFoundResponse(responseDTO, "User not found.");
        }
        return responseDTO;
    }
}
