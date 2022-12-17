package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.dto.CourseDTO;
import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.services.CourseService;
import com.api.springstudentsapi.services.StudentService;
import com.api.springstudentsapi.services.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    private final CourseService courseService;


    @Autowired
    public CourseController(CourseService courseService, StudentService studentService, TeacherService teacherService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return this.courseService.getAllCourses()
                .stream()
                .map(course -> {
                    CourseDTO courseDTO = new CourseDTO();
                    BeanUtils.copyProperties(course, courseDTO);
                    return courseDTO;
                })
                .toList();
    }

    @GetMapping("course")
    public Course getCourseById(@RequestParam(name = "cid") Long courseId) {
        return this.courseService.getCourseById(courseId);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course aCourse) {
        return this.courseService.addCourse(aCourse);
    }

    @DeleteMapping(path = "delete")
    void deleteCourse(@RequestParam(name = "cid") Long id) {
        this.courseService.deleteCourse(id);
    }

    @PutMapping(path = "update")
    public void updateCourse(
            @RequestParam(name = "cid") Long courseId,
            @RequestParam(name = "newName") String newCourseName
    ){
        this.courseService.updateCourseById(courseId, newCourseName);
    }
}
