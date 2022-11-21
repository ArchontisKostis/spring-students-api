package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.repositories.CourseRepository;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, RegistrationRepository registrationRepository) {
        this.courseRepository = courseRepository;
        this.registrationRepository = registrationRepository;
    }

    public Collection<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (!courseOptional.isPresent())
            throw new RuntimeException("Course not found in database. ID: " + id);

        Course foundCourse = courseOptional.get();
        return foundCourse;
    }

    public Course addCourse(Course aCourse) {
        return this.courseRepository.save(aCourse);
    }

}
