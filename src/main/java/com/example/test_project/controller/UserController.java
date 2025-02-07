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

    /**
     * API to create a new user.
     * @param userRequestDTO - The user details sent in the request body.
     * @return ResponseEntity containing the status and response message.
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO, BindingResult bindingResult){
        log.info("UserController.createUser() method accessed..");
        ResponseDTO responseDTO = userService.createUser(userRequestDTO,bindingResult);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    /**
     * API to retrieve the list of all users.
     * @return ResponseEntity containing the list of users.
     */
    @GetMapping
    public ResponseEntity<ResponseDTO> getUsers(){
        log.info("UserController.getUsers() method accessed..");
        ResponseDTO responseDTO = userService.getUserList();
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    /**
     * API to get details of a specific user by ID.
     * @param id - The ID of the user to retrieve.
     * @return ResponseEntity containing the course details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable Integer id){
        log.info("UserController.getUserById() method accessed..");
        ResponseDTO responseDTO = userService.getUserById(id);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }


}
