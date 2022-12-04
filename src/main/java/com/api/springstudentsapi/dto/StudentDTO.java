package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Collection<RegistrationDTO> enrolledCourses;

    public static StudentDTO convert(Student student) {
        Collection<RegistrationDTO> studentRegistrations = DTOMapper.mapToRegistrationDTOList(student.getStudentRegistrations());
        return new StudentDTO(student.getId(), student.getName(), studentRegistrations);
    }
}
