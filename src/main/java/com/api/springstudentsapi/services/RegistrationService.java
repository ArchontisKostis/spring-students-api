package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository,
                               StudentService studentService,
                               CourseService courseService) {
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

    public Registration getRegistrationsByStudentAndCourseId(Long studentId, Long courseId) {
        Optional<Registration> registration =
                this.registrationRepository.getRegistrationByStudentAndCourseId(studentId, courseId)
                .stream().findFirst();

        if(registration.isEmpty())
            throw new RuntimeException("Registration does not exist.");

        return registration.get();
    }

    public void updateStudentRegistration(Registration registration) {
        registrationRepository.save(registration);
    }
}
