package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.entities.Teaching;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class DTOMapper {
    public static TeacherDTO convertToTeacherDTO(Teacher teacher) {
        List<Teaching> teachings = teacher.getTeacherTeachings();

        List<TeachingCourseDTO> teachingsDTOs =
                teachings.stream()
                        .map(teaching -> convertToTeachingCourseDTO(teaching))
                        .toList();

        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(teacher, teacherDTO);
        teacherDTO.setTeachingCourses(teachingsDTOs);

        return teacherDTO;
    }

    public static StudentDTO convertToStudentDTO(Student student) {
        List<Registration> registrations = student.getStudentRegistrations();
        List<EnrolledCourseDTO> registrationDTOs =
                registrations.stream()
                        .map(registration -> convertToEnrolledCourseDTO(registration))
                        .toList();

        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(student, studentDTO);
        studentDTO.setEnrolledCourses(registrationDTOs);

        return studentDTO;
    }

    public static RegistrationDTO convertToRegistrationDTO(Registration registration) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        BeanUtils.copyProperties(registration, registrationDTO);

        return registrationDTO;
    }

    private static TeachingCourseDTO convertToTeachingCourseDTO(Teaching teaching) {
        TeachingCourseDTO teachingCourseDTO = new TeachingCourseDTO();
        BeanUtils.copyProperties(teaching, teachingCourseDTO);

        return teachingCourseDTO;
    }

    private static EnrolledCourseDTO convertToEnrolledCourseDTO(Registration registration) {
        EnrolledCourseDTO enrolledCourseDTO = new EnrolledCourseDTO();
        BeanUtils.copyProperties(registration, enrolledCourseDTO);

        return enrolledCourseDTO;
    }

    private DTOMapper() {
        throw new IllegalStateException("Utility class");
    }
}
