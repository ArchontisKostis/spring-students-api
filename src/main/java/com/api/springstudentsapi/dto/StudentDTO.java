package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public static StudentDTO convert(Student student) {
        return new StudentDTO(student.getId(), student.getName());
    }
}
