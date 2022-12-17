package com.api.springstudentsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;
}
