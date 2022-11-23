package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return this.teacherService.getAllTeachers();
    }

    @GetMapping(path = "getTeacher")
    public Teacher getTeacherById(@RequestParam(name = "tid") Long teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher){
        return this.teacherService.addTeacher(teacher);
    }

}
