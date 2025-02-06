package com.example.test_project.repository;

import com.example.test_project.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByTitle(String title);
}
