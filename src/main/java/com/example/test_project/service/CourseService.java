package com.example.test_project.service;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.CourseRequestDTO;

public interface CourseService {
    ResponseDTO saveCourse(CourseRequestDTO courseRequestDTO);
}
