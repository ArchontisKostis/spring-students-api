package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.dto.DTOMapper;
import com.api.springstudentsapi.dto.TeacherDTO;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = this.teacherService.getAllTeachers();

        return teachers.stream()
                .map(teacher -> DTOMapper.convertToTeacherDTO(teacher))
                .toList();
    }

    @GetMapping(path = "getTeacher")
    public TeacherDTO getTeacherById(@RequestParam(name = "tid") Long teacherId) {
        Teacher foundTeacher = teacherService.getTeacherById(teacherId);
        return DTOMapper.convertToTeacherDTO(foundTeacher);
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher){
        return this.teacherService.addTeacher(teacher);
    }

    @DeleteMapping(path = "delete")
    public void deleteTeacher(@RequestParam(name = "tid") Long id) {
        this.teacherService.deleteTeacher(id);
    }

    @PutMapping(path = "update")
    public void updateTeacher(
            @RequestParam(name = "tid") Long teacherId,
            @RequestParam(name = "newName") String newName
    ) {
        this.teacherService.updateTeacher(teacherId, newName);
    }

    @PutMapping(path = "setGrade")
    public void setGradeToStudent(
            @RequestParam(name = "tid") Long teacherId,
            @RequestParam(name = "sid") Long studentId,
            @RequestParam(name = "cid") Long courseId,
            @RequestParam(name = "grade") int grade
    ){
        teacherService.setGradeToStudent(teacherId, studentId, courseId, grade);
    }

}
