package com.example.test_project.dto.requestDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {
    private String title;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
}
