package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.services.CourseService;
import com.api.springstudentsapi.services.RegistrationService;
import com.api.springstudentsapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public RegistrationController(RegistrationService registrationService, StudentService studentService, CourseService courseService) {
        this.registrationService = registrationService;
        this.studentService = studentService;
        this.courseService = courseService;
    }
    
    @PostMapping(path = "register")
    public void registerStudentToCourse(@RequestParam(name = "student") Long studentId, @RequestParam(name = "course") Long courseId) {
        // This belongs in the service layer
        Course foundCourse = courseService.getCourseById(courseId);
        Student foundStudent = studentService.getStudentById(studentId);
        //

        registrationService.registerStudentToCourse(foundStudent, foundCourse);
    }
}
