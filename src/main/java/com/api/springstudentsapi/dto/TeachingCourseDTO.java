package com.api.springstudentsapi.dto;

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
        return new TeachingCourseDTO(teaching.getCourse().getId(), teaching.getCourse().getName());
    }
}
