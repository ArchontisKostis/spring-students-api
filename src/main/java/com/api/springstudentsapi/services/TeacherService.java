package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    public Teacher addTeacher(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }
}
