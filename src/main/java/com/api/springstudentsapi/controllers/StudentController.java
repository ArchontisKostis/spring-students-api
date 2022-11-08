package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return this.studentService.addStudent(student);
    }

    @DeleteMapping
    void deleteStudent(Long id) {
        this.studentService.deleteStudentById(id);
    }

    @PutMapping(path = "delete/{studentId}")
    public void updateStudent(
            @PathVariable Long studentId,
            @RequestParam String newName
    ) {
        Student student = studentService.getStudentById(studentId);
        studentService.updateStudentById(student, newName);
    }

}
