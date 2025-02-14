package com.example.test_project.controller;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.QuizRequestDTO;
import com.example.test_project.service.QuizService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
@Slf4j
public class QuizController {

    @Autowired
    private QuizService quizService;

    /**
     * API to create a new quiz.
     * @param quizRequestDTO - The course details sent in the request body.
     * @return ResponseEntity containing the status and response message.
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> saveQuiz (@RequestBody QuizRequestDTO quizRequestDTO){
        log.info("QuizController.saveCourse method accessed..");
        ResponseDTO responseDTO = quizService.saveQuiz(quizRequestDTO);
        return ResponseEntity.status((responseDTO.getStatus())).body(responseDTO);
    }

}
