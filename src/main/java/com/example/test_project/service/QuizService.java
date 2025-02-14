package com.example.test_project.service;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.QuizRequestDTO;

public interface QuizService {
    ResponseDTO saveQuiz(QuizRequestDTO quizRequestDTO);
}
