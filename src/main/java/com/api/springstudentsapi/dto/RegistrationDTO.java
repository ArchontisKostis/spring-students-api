package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

public class RegistrationDTO {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int grade;

    public static RegistrationDTO convert(Registration registration) {
        String courseName = registration.getCourse().getName();
        int grade = registration.getGrade();
        return new RegistrationDTO(courseName, grade);
    }
}
