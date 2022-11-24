package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.exceptions.teacher.TeacherNotFoundException;
import com.api.springstudentsapi.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Teacher getTeacherById(Long anId) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(anId);

        if(teacherOptional.isEmpty())
            throw new TeacherNotFoundException("Teacher not found.");

        Teacher foundTeacher = teacherOptional.get();
        return foundTeacher;
    }

    public Teacher addTeacher(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        Teacher teacherToDelete = getTeacherById(id);
        teacherRepository.delete(teacherToDelete);
    }
}
