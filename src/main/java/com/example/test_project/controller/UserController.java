package com.example.test_project.controller;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.UserRequestDTO;
import com.example.test_project.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO, BindingResult bindingResult){
        log.info("UserController.createUser() method accessed..");
        ResponseDTO responseDTO = userService.createUser(userRequestDTO,bindingResult);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getUsers(){
        log.info("UserController.getUsers() method accessed..");
        ResponseDTO responseDTO = userService.getUserList();
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
    @GetMapping
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable Integer userId){
        log.info("UserController.getUserById() method accessed..");
        ResponseDTO responseDTO = userService.getUserById(userId);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
