package com.example.test_project.service.impl;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.QuizRequestDTO;
import com.example.test_project.entity.Course;
import com.example.test_project.entity.Quiz;
import com.example.test_project.repository.CourseRepository;
import com.example.test_project.repository.QuizRepository;
import com.example.test_project.service.QuizService;
import com.example.test_project.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class QuizServiceIMPL implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public ResponseDTO saveQuiz(QuizRequestDTO quizRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO<>();
        Optional<Course> course= courseRepository.findById(quizRequestDTO.getCourse());
        Optional<Course> existingQuiz = quizRepository.findByQuestion(quizRequestDTO.getQuestion());
        if(existingQuiz.isPresent()){
            return ResponseUtil.handleConflictResponse(responseDTO,"Question is already in use");
        }
        else{
            Quiz quiz = new Quiz();
            quiz.setCourse(course.get());
            quiz.setQuestion(quizRequestDTO.getQuestion());
            quiz.setAnswer(quizRequestDTO.getAnswer());
            quiz.setOptions(quizRequestDTO.getOptions());
            quiz.setCreatedAt(LocalDateTime.now());

            quizRepository.save(quiz);
            responseDTO.setData(quiz);
            ResponseUtil.handleCreateResponse(responseDTO,quiz,"Quiz Created Successfully");
        }

        return responseDTO;
    }
}
