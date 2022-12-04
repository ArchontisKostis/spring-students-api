package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DTOMapper {
    public static List<StudentDTO> mapToStudentDTOList(Collection<Student> studentList) {
        Stream<Student> studentsStream = studentList.stream();
        List<StudentDTO> studentDTOS =
                studentsStream
                        .map(student -> StudentDTO.convert(student))
                        .collect(Collectors.toList());

        return studentDTOS;
    }

    public static Collection<RegistrationDTO> mapToRegistrationDTOList(List<Registration> registrations) {
        Stream<Registration> registrationStream = registrations.stream();
        Stream<RegistrationDTO> registrationDTOStream =
                registrationStream.map(registration -> RegistrationDTO.convert(registration));

        return registrationDTOStream.toList();
    }
}
