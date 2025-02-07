package com.example.test_project.controller;

import com.example.test_project.dto.ResponseDTO;
import com.example.test_project.dto.requestDTOs.CourseRequestDTO;
import com.example.test_project.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * API to create a new course.
     * @param courseRequestDTO - The course details sent in the request body.
     * @return ResponseEntity containing the status and response message.
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> saveCourse (@RequestBody CourseRequestDTO courseRequestDTO){
        log.info("CourseController.saveCourse method accessed..");
        ResponseDTO responseDTO = courseService.saveCourse(courseRequestDTO);
        return ResponseEntity.status((responseDTO.getStatus())).body(responseDTO);
    }

    /**
     * API to retrieve the list of all courses.
     * @return ResponseEntity containing the list of courses.
     */

    @GetMapping
    public ResponseEntity<ResponseDTO> getCourses(){
        log.info("CourseController.getCourses method accessed..");
        ResponseDTO responseDTO = courseService.getCourses();
        return ResponseEntity.status((responseDTO.getStatus())).body(responseDTO);
    }
    /**
     * API to update an existing course.
     * @param courseRequestDTO - The updated course details sent in the request body.
     * @param id - The ID of the course to be updated (passed in the URL path).
     * @return ResponseEntity with the update status and response message.
     */

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateCourse(@RequestBody CourseRequestDTO courseRequestDTO, @PathVariable Integer id){
        log.info("CourseController.updateCourse() method accessed..");
        ResponseDTO responseDTO = courseService.updateCourse(courseRequestDTO,id);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
    /**
     * API to get details of a specific course by ID.
     * @param id - The ID of the course to retrieve.
     * @return ResponseEntity containing the course details.
     */

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getCourseById(@PathVariable Integer id){
        log.info("CourseController.updateCourse() method accessed..");
        ResponseDTO responseDTO = courseService.getCourseById(id);
        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }


}
