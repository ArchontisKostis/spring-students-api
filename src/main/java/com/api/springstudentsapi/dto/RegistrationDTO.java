package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    @Getter @Setter
    private Student student;
    @Getter @Setter
    private Course course;
}
