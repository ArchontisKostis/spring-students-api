package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
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
    }

    public Registration setGradeToStudent(Student student, Course course, int grade) {
        if(!student.isEnrolledToCourse(course) || !this.canGradeCourse(course))
            throw new RuntimeException("setGradeToStudent()");

        Registration studentRegistration = student.getRegistrationByCourse(course);
        studentRegistration.setGrade(grade);
        return studentRegistration;
    }

    public boolean isTeachingCourse(Course course) {
        boolean courseExists = this.teacherTeachings.stream()
                .anyMatch(teaching -> teaching.getCourse() == course);

        return courseExists;
    }

    public boolean canGradeCourse(Course course) {
        if (this.isTeachingCourse(course))
            return true;
        return false;
    }
}
