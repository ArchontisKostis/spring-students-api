package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.services.CourseService;
import com.api.springstudentsapi.services.TeacherService;
import com.api.springstudentsapi.services.TeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/teaching")
public class TeachingController {
    private final TeachingService teachingService;
    private final TeacherService teacherService;
    private final CourseService courseService;

    @Autowired
    public TeachingController(TeachingService teachingService, TeacherService teacherService, CourseService courseService) {
        this.teachingService = teachingService;
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @PostMapping(path = "assign")
    public void assignTeacherToCourse(
            @RequestParam(name = "tid") Long teacherId,
            @RequestParam(name = "cid") Long courseId
    ) {
        teachingService.assignTeacherToCourse(teacherId, courseId);
    }
}
