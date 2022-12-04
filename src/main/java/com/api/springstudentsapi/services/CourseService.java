package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.exceptions.course.CourseNotFoundException;
import com.api.springstudentsapi.repositories.CourseRepository;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Collection<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course not found." + id));
    }

    public Course addCourse(Course aCourse) {
        return this.courseRepository.save(aCourse);
    }

    public void deleteCourse(Long id) {
        Course courseToDelete = getCourseById(id);
        courseRepository.delete(courseToDelete);
    }

    public void updateCourseById(Long id, String newName) {
        Course courseToUpdate = getCourseById(id);
        courseToUpdate.setName(newName);
        courseRepository.save(courseToUpdate);
    }

}
