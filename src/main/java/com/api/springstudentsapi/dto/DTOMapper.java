package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.entities.Teaching;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DTOMapper {
    public static List<StudentDTO> mapToStudentDTOList(List<Student> studentList) {
        Stream<Student> studentsStream = studentList.stream();
        List<StudentDTO> studentDTOS =
                studentsStream
                        .map(student -> {
                            StudentDTO studentDTO = new StudentDTO();
                            BeanUtils.copyProperties(student, studentDTO);

                            studentDTO.setEnrolledCourses(mapToEnrolledCoursesDTOList(student.getStudentRegistrations()));

                            return studentDTO;
                        })
                        .toList();

        return studentDTOS;
    }

    public static List<EnrolledCourseDTO> mapToEnrolledCoursesDTOList(List<Registration> registrations) {
        Stream<Registration> registrationStream = registrations.stream();
        Stream<EnrolledCourseDTO> registrationDTOStream =
                registrationStream.map(registration -> {
                    EnrolledCourseDTO enrolledCourseDTO = new EnrolledCourseDTO();
                    BeanUtils.copyProperties(registration, enrolledCourseDTO);

                    return enrolledCourseDTO;
                });

        return registrationDTOStream.toList();
    }

    public static List<RegistrationDTO> mapToRegistrationDTOList(List<Registration> registrations) {
        Stream<Registration> registrationStream = registrations.stream();
        Stream<RegistrationDTO> registrationDTOStream =
                registrationStream.map(registration -> {
                    RegistrationDTO registrationDTO = new RegistrationDTO();
                    BeanUtils.copyProperties(registration, registrationDTO);

                    return registrationDTO;
                });

        return registrationDTOStream.toList();
    }

    public static List<TeacherDTO> mapToTeachersList(List<Teacher> teachers){
        Stream<Teacher> teacherStream = teachers.stream();
        Stream<TeacherDTO> teacherDTOStream =
                teacherStream.map(teacher -> {
                    TeacherDTO teacherDTO = new TeacherDTO();
                    BeanUtils.copyProperties(teacher, teacherDTO);

                    teacherDTO.setTeachingCourses(mapToTeachingCourseDTOList(teacher.getTeacherTeachings()));

                    return teacherDTO;
                });

        return teacherDTOStream.toList();
    }

    public static List<TeachingCourseDTO> mapToTeachingCourseDTOList(List<Teaching> teachings){
        Stream<Teaching> teachingStream = teachings.stream();
        Stream<TeachingCourseDTO> teachingCourseDTOStream =
                teachingStream.map(teaching -> {
                    TeachingCourseDTO teachingCourseDTO = new TeachingCourseDTO();
                    BeanUtils.copyProperties(teaching, teachingCourseDTO);

                    return teachingCourseDTO;
                });

        return teachingCourseDTOStream.toList();
    }
}
