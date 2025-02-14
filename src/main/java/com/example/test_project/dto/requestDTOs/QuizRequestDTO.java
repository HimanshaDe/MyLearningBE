package com.example.test_project.dto.requestDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequestDTO {
   private Integer course;

    private String question;

    private String answer;

    private List<String> options;

    private LocalDateTime createdAt;
}
