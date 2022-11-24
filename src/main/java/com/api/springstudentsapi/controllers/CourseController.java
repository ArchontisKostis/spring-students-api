package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.services.CourseService;
import com.api.springstudentsapi.services.StudentService;
import com.api.springstudentsapi.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;
    private final TeacherService teacherService;


    @Autowired
    public CourseController(CourseService courseService, StudentService studentService, TeacherService teacherService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public Collection<Course> getAllCourses() {
        return this.courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long courseId) {
        return this.courseService.getCourseById(courseId);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course aCourse) {
        return this.courseService.addCourse(aCourse);
    }


}
