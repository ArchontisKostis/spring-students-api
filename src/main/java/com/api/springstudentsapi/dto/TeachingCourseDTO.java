package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Teaching;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class TeachingCourseDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String course;

    public static TeachingCourseDTO convert(Teaching teaching) {
        Course teachingCourse = teaching.getCourse();
        String courseName = teachingCourse.getName();
        Long courseId = teachingCourse.getId();

        return new TeachingCourseDTO(courseId, courseName);
    }
}
