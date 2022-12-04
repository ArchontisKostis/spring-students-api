package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private List<TeachingCourseDTO> teachingCourses;

    public static TeacherDTO convert(Teacher teacher) {
        return new TeacherDTO(teacher.getId(), teacher.getName(), DTOMapper.mapToTeachingCourseDTOList(teacher.getTeacherTeachings()));
    }
}
