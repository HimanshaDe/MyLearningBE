package com.example.test_project.dto.requestDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String name;

    private String email;

    private String password;

    private String role;

}
