package com.example.test_project.service;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.UserRequestDTO;

public interface UserService  {
    ResponseDTO createUser(UserRequestDTO userRequestDTO);
}
