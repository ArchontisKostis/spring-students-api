package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class TeachingCourseDTO {
    @Getter @Setter
    private Course course;
}
