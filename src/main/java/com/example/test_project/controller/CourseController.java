package com.example.test_project.controller;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.CourseRequestDTO;
import com.example.test_project.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<ResponseDTO> saveCourse (@RequestBody CourseRequestDTO courseRequestDTO){
        log.info("CourseController.saveCourse method accessed..");

        ResponseDTO responseDTO = courseService.saveCourse(courseRequestDTO);
        return ResponseEntity.status((responseDTO.getStatus())).body(responseDTO);
    }
}
