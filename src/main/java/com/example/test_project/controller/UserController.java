package com.example.test_project.controller;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.UserRequestDTO;
import com.example.test_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> saveEmployee(@RequestBody UserRequestDTO userRequestDTO){
        log.info("EmployeeController.saveEmployee() method accessed..");
        ResponseDTO responseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
