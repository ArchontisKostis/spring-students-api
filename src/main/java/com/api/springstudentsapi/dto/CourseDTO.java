package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int grade;

    public static CourseDTO convert(Course course, int grade) {
        return new CourseDTO(course.getName(), grade);
    }
}
