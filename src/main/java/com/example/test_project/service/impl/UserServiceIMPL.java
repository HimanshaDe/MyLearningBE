package com.example.test_project.service.impl;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.UserRequestDTO;
import com.example.test_project.entity.User;
import com.example.test_project.repository.UserRepository;
import com.example.test_project.service.UserService;
import com.example.test_project.util.ResponseUtil;
import com.example.test_project.util.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;
    @Override
    public ResponseDTO createUser(UserRequestDTO userRequestDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new ResponseDTO();

        // Check if the email already exists
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            return ResponseUtil.handleConflictResponse(responseDTO, "Email is already in use");
        }

        // Check if the name already exists
        if (existingUser.isPresent() &&
                userValidator.convertToCamelCase(existingUser.get().getName()).equals(userRequestDTO.getName())) {
            return ResponseUtil.handleConflictResponse(responseDTO, "Name is already in use");
        }

        // Handle validation errors
        if (bindingResult.hasErrors()) {
            ResponseDTO errorResponse = new ResponseDTO();
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setErrors(bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList()));
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST).getBody();
        }

        // Validate email format
        if (!userValidator.isValideEmail(userRequestDTO.getEmail())) {
            return ResponseUtil.handleIncorrectEntityResponse(responseDTO, "Invalid email format");
        }

        // Hash the password
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = bCryptPasswordEncoder.encode(userRequestDTO.getPassword());

        // Convert name to camel case
        String camelCaseName = userValidator.convertToCamelCase(userRequestDTO.getName());

        // Create the new user
        User user = new User();
        user.setName(camelCaseName);
        user.setEmail(userRequestDTO.getEmail());
        user.setRole(userRequestDTO.getRole());
        user.setPassword(hashedPassword);

        // Save the user
        userRepository.save(user);

        // Prepare success response
        ResponseDTO successResponse = new ResponseDTO();
        ResponseUtil.handleCreateResponse(successResponse, user, "User created successfully");

        return successResponse;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Retrieve the user from the database by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Get the user's role and assign it as a GrantedAuthority
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().name()));

        // Create and return a UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities // Pass authorities here
        );
    }

}
