package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.exceptions.teacher.TeacherNotFoundException;
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

    public Teacher getTeacherById(Long anId) {
        return teacherRepository.findById(anId).orElseThrow(() -> new TeacherNotFoundException("Did not find Teacher."));
    }

    public Teacher addTeacher(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        Teacher teacherToDelete = getTeacherById(id);
        teacherRepository.delete(teacherToDelete);
    }

    public void updateTeacher(Long id, String newName) {
        Teacher teacherToUpdate = getTeacherById(id);
        teacherToUpdate.setName(newName);
        this.teacherRepository.save(teacherToUpdate);
    }
}
