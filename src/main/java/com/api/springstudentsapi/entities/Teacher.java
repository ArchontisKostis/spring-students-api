package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacherIdGenerator",
            sequenceName = "teacherSequennce",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "teacherIdGenerator"
    )
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    @Getter @Setter
    private List<Teaching> teacherTeachings;

    public Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
        this.teacherTeachings = new ArrayList<>();
    }

    public Registration setGradeToStudent(Registration studentRegistration, int grade) {
        Course courseToGrade = studentRegistration.getCourse();
        if (!this.isTeachingCourse(courseToGrade))
            throw  new RuntimeException("Cannot grade this course because you are not teaching it");

        studentRegistration.setGrade(grade);
        return studentRegistration;
    }

    public boolean isTeachingCourse(Course course) {
        boolean courseExists = this.teacherTeachings.stream()
                .anyMatch(teaching -> teaching.getCourse() == course);

        return courseExists;
    }
}
