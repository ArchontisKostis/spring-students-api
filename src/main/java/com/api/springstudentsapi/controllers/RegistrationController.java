package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.services.CourseService;
import com.api.springstudentsapi.services.RegistrationService;
import com.api.springstudentsapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

    @GetMapping
    public Collection<Registration> getRegistrations(){ return this.registrationService.getRegistrations();}

    @PutMapping
    public Registration registerStudentToCourse() {
        Student student = studentService.getStudentById(1L);
        Course course = courseService.getCourseById(1L);

       return registrationService.registerStudentToCourse(student, course);
    }
}
