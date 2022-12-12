package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, StudentService studentService, CourseService courseService) {
        this.registrationRepository = registrationRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public List<Registration> getRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration registerStudentToCourse(Long studentId, Long courseId) {
        Student studentToRegister = studentService.getStudentById(studentId);
        Course courseToEnroll = courseService.getCourseById(courseId);

        Registration registration = new Registration(studentToRegister, courseToEnroll);
        return registrationRepository.save(registration);
    }
}
