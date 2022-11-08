package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return this.courseService.getAllCourses();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course aCourse){
        return this.courseService.addCourse(aCourse);
    }
}
