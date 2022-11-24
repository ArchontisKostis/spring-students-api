package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.exceptions.student.StudentNotFound;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import com.api.springstudentsapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final RegistrationRepository registrationRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, RegistrationRepository registrationRepository) {
        this.studentRepository = studentRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFound("Student with id: " + id + " not found in database."));
    }

    public Student addStudent(Student aStudent) {
        return this.studentRepository.save(aStudent);
    }

    public void deleteStudentById(Long id) {
        boolean idExists = studentRepository.existsById(id);

        if (!idExists)
            throw new StudentNotFound("Student to delete not found");
        studentRepository.deleteById(id);
    }

    public void updateStudentById(Long id, String newName) {
        Student student = getStudentById(id);
        student.setName(newName == null ? student.getName() : newName);
        studentRepository.save(student);
    }

}
