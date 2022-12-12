package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor

public class EnrolledCourseDTO {
    @Getter @Setter
    private Course course;
    @Getter @Setter
    private int grade;
}
