package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Registration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

public class EnrolledCourseDTO {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int grade;

    public static EnrolledCourseDTO convert(Registration registration) {
        String courseName = registration.getCourse().getName();
        int grade = registration.getGrade();
        return new EnrolledCourseDTO(courseName, grade);
    }
}
