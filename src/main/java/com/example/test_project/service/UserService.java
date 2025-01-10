package com.example.test_project.service;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.UserRequestDTO;
import org.springframework.validation.BindingResult;

public interface UserService  {
    ResponseDTO createUser(UserRequestDTO userRequestDTO, BindingResult bindingResult);

    ResponseDTO getUserList();

    ResponseDTO getUserById(Integer userId);
}
