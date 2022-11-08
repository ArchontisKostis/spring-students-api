package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.services.CourseService;
import com.api.springstudentsapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;


    @Autowired
    public CourseController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return this.courseService.getAllCourses();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course aCourse){
        return this.courseService.addCourse(aCourse);
    }

    @PutMapping("enrollStudent/course/{courseId}/student/{studentId}")
    Course enrollStudentToCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ){
        Course course = courseService.findCourseById(courseId);
        Student student = studentService.getStudentById(studentId);
        course.enrollStudent(student);

        return courseService.addCourse(course);
    }
}
