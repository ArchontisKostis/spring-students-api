package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.services.CourseService;
import com.api.springstudentsapi.services.RegistrationService;
import com.api.springstudentsapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(path = "register/student/{studentId}/course/{courseId}")
    public void registerStudentToCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        Course foundCourse = courseService.getCourseById(courseId);
        Student foundStudent = studentService.getStudentById(studentId);
        registrationService.registerStudentToCourse(foundStudent, foundCourse);
    }
}
