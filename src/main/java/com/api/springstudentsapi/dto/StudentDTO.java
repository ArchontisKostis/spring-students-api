package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Collection<EnrolledCourseDTO> enrolledCourses;

    public static StudentDTO convert(Student student) {
        Collection<EnrolledCourseDTO> studentRegistrations = DTOMapper.mapToEnrolledCoursesDTOList(student.getStudentRegistrations());
        return new StudentDTO(student.getId(), student.getName(), studentRegistrations);
    }
}
