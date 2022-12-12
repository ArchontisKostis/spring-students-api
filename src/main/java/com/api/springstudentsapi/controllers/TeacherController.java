package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.dto.DTOMapper;
import com.api.springstudentsapi.dto.TeacherDTO;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.entities.Teaching;
import com.api.springstudentsapi.services.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = this.teacherService.getAllTeachers();
        return DTOMapper.mapToTeachersList(teachers);
    }

    @GetMapping(path = "getTeacher")
    public TeacherDTO getTeacherById(@RequestParam(name = "tid") Long teacherId) {
        Teacher foundTeacher = teacherService.getTeacherById(teacherId);
        List<Teaching> teachings = foundTeacher.getTeacherTeachings();

        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(foundTeacher, teacherDTO);
        teacherDTO.setTeachingCourses(DTOMapper.mapToTeachingCourseDTOList(teachings));

        return teacherDTO;
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

}
