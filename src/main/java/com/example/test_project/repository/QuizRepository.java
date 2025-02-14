package com.example.test_project.repository;

import com.example.test_project.entity.Course;
import com.example.test_project.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {

    Optional<Course> findByQuestion(String question);
}
