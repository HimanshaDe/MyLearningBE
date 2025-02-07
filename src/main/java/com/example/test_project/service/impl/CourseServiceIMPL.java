package com.example.test_project.service.impl;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.CourseRequestDTO;
import com.example.test_project.entity.Course;
import com.example.test_project.repository.CourseRepository;
import com.example.test_project.service.CourseService;
import com.example.test_project.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseServiceIMPL implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public ResponseDTO saveCourse(CourseRequestDTO courseRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO<>();

        Optional<Course> existingCourse = courseRepository.findByTitle(courseRequestDTO.getTitle());
        if(existingCourse.isPresent()){
            return ResponseUtil.handleConflictResponse(responseDTO,"Course is already in use");
        }
        else{
            Course course = new Course();
            course.setTitle(courseRequestDTO.getTitle());
            course.setDescription(courseRequestDTO.getDescription());
            course.setCreatedAt(LocalDateTime.now());
            course.setCreatedBy(courseRequestDTO.getCreatedBy());

            courseRepository.save(course);
            responseDTO.setData(course);
            ResponseUtil.handleCreateResponse(responseDTO,course,"Course Created Successfully");
        }

        return responseDTO;
    }

    @Override
    public ResponseDTO getCourses() {
        ResponseDTO responseDTO = new ResponseDTO<>();
        List<Course> courses = courseRepository.findAll();

        if(courses.isEmpty()){
            ResponseUtil.handleNotFoundResponse(responseDTO,"Course list is empty");
        }else{
            ResponseUtil.handleOkResponse(responseDTO,courses,"User List retrieved successfully ");
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO updateCourse(CourseRequestDTO courseRequestDTO, Integer id) {
        log.info("CourseServiceIMPL.updateCourse() method accessed..");
        ResponseDTO responseDTO = new ResponseDTO<>();

        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty()){
            return ResponseUtil.handleNotFoundResponse(responseDTO,"Course not found");
        }
        Course currentCourse = courseOptional.get();
        currentCourse.setTitle(courseRequestDTO.getTitle());
        currentCourse.setDescription(courseRequestDTO.getDescription());
        courseRepository.save(currentCourse);
        ResponseUtil.handleCreateResponse(responseDTO,currentCourse,"Course updated successfully");
        return responseDTO;
    }

    @Override
    public ResponseDTO getCourseById(Integer id) {
        log.info("CourseServiceIMPL.getCourseById() method accessed..");
        ResponseDTO responseDTO = new ResponseDTO<>();

        Optional<Course> courseOptional = courseRepository.findById(id);

        if(courseOptional.isEmpty()){
             ResponseUtil.handleNotFoundResponse(responseDTO,"Course not found");
        }
        else {
            ResponseUtil.handleOkResponse(responseDTO,courseOptional.get(),"Course retrieved successfully");
        }

        return responseDTO;
    }


}
