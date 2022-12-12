package com.api.springstudentsapi.controllers;

import com.api.springstudentsapi.dto.DTOMapper;
import com.api.springstudentsapi.dto.StudentDTO;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.services.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return DTOMapper.mapToStudentDTOList(students);
    }

    @GetMapping(path = "getStudent")
    public StudentDTO getStudentById(@RequestParam(name = "sid") Long studentId) {
        Student foundStudent = studentService.getStudentById(studentId);
        List<Registration> registrations = foundStudent.getStudentRegistrations();

        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(foundStudent, studentDTO);
        studentDTO.setEnrolledCourses(DTOMapper.mapToEnrolledCoursesDTOList(foundStudent.getStudentRegistrations()));

        return studentDTO;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.addStudent(student);
    }

    @DeleteMapping(path = "delete")
    void deleteStudent(@RequestParam(name = "sid") Long id) {
        this.studentService.deleteStudentById(id);
    }

    @PutMapping(path = "update")
    public void updateStudent(
            @RequestParam(name = "sid") Long studentId,
            @RequestParam(name = "newName") String newName
    ) {
        studentService.updateStudentById(studentId, newName);
    }

}
