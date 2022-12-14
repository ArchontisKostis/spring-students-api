package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.exceptions.teacher.TeacherNotFoundException;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import com.api.springstudentsapi.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final CourseService courseService;
    private final StudentService studentService;
    private final RegistrationRepository registrationRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, CourseService courseService, StudentService studentService, RegistrationRepository registrationRepository) {
        this.teacherRepository = teacherRepository;
        this.courseService = courseService;
        this.studentService = studentService;
        this.registrationRepository = registrationRepository;
    }

    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long anId) {
        return teacherRepository.findById(anId).orElseThrow(() -> new TeacherNotFoundException("Teacher not found."));
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

    public void setGradeToStudent(Long teacherId, Long studentId, Long courseId, int grade) {
        Teacher teacher = getTeacherById(teacherId);
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        Registration updatedStudentRegistration = teacher.setGradeToStudent(student, course, grade);
        registrationRepository.save(updatedStudentRegistration);
    }
}
