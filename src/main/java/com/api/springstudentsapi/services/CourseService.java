package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public Course addCourse(Course aCourse) {
        return this.courseRepository.save(aCourse);
    }

    public Course findCourseById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (!courseOptional.isPresent())
            throw new RuntimeException("Course not found in database. ID: " + id);

        Course foundCourse = courseOptional.get();
        return foundCourse;
    }
}
