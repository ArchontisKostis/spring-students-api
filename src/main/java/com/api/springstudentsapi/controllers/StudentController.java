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

    @GetMapping(path = "getStudent")
    public Student getStudentById(@RequestParam(name = "sid") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.addStudent(student);
    }

    //Get id from PathVariable
    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable Long id) {
        this.studentService.deleteStudentById(id);
    }


    //Get updated values as Request Body from a DTO
    //What if the object has 20 fields?...Too many RequestParams
    //PathVariables and RequestParams are shown in the url which are a security threat
    @PutMapping(path = "update")
    public void updateStudent(
            @RequestParam(name = "sid") Long studentId,
            @RequestParam(name = "newName") String newName
    ) {
        studentService.updateStudentById(studentId, newName);
    }

}
