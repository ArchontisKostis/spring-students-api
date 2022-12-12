package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.exceptions.student.StudentNotFoundException;
import com.api.springstudentsapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found."));
    }

    public Student addStudent(Student aStudent) {
        return this.studentRepository.save(aStudent);
    }

    public void deleteStudentById(Long id) {
        Student studentToDelete = getStudentById(id);
        studentRepository.delete(studentToDelete);
    }

    public void updateStudentById(Long id, String newName) {
        Student student = getStudentById(id);
        student.setName(newName == null ? student.getName() : newName);
        studentRepository.save(student);
    }

}
