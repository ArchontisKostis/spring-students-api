package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.entities.Teaching;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class DTOMapper {
    public static Object convertToDTO(Object objectToConvert, Object dtoObject) {
        BeanUtils.copyProperties(objectToConvert, dtoObject);
        return dtoObject;
    }

    public static TeacherDTO convertToTeacherDTO(Teacher teacher) {
        List<Teaching> teachings = teacher.getTeacherTeachings();

        List<TeachingCourseDTO> teachingsDTOs =
                teachings.stream()
                        .map(teaching ->
                                (TeachingCourseDTO) convertToDTO(teaching, new TeachingCourseDTO()))
                        .toList();

        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setTeachingCourses(teachingsDTOs);
        convertToDTO(teacher, teacherDTO);

        return teacherDTO;
    }

    public static StudentDTO convertToStudentDTO(Student student) {
        List<Registration> registrations = student.getStudentRegistrations();
        List<EnrolledCourseDTO> registrationDTOs =
                registrations.stream()
                        .map(registration ->
                                (EnrolledCourseDTO) convertToDTO(registration, new EnrolledCourseDTO()))
                        .toList();

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setEnrolledCourses(registrationDTOs);
        convertToDTO(student, studentDTO);

        return studentDTO;
    }

    private DTOMapper() {
        throw new IllegalStateException("Utility class");
    }
}
