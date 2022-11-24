package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.exceptions.student.StudentNotFound;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import com.api.springstudentsapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            throw new StudentNotFound("Student not found.");

        Student foundStudent = studentOptional.get();
        return foundStudent;
    }

    public Student addStudent(Student aStudent){
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
        student.setName(newName);
        studentRepository.save(student);
    }

}
