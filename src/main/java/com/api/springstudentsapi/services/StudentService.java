package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            throw new RuntimeException("Student not found in database. ID: " + id);

        Student foundStudent = studentOptional.get();
        return foundStudent;
    }

    public Student addStudent(Student aStudent){
        return this.studentRepository.save(aStudent);
    }

    public void deleteStudentById(Long id) {
        boolean idExists = studentRepository.existsById(id);

        if (!idExists)
            throw new RuntimeException("Student to delete not found");
        studentRepository.deleteById(id);
    }

    public void updateStudentById(Long id, String newName) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            throw new RuntimeException("Student not found in database. ID: " + id);

        Student student = studentOptional.get();
        student.setName(newName);
        studentRepository.save(student);
    }

}
