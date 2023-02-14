package com.api.springstudentsapi.entities;

import com.api.springstudentsapi.exceptions.course.CourseNotFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Student {
    @Id
    @SequenceGenerator(
            name = "studentIdGenerator",
            sequenceName = "studentSequennce",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "studentIdGenerator"
    )
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    @Getter @Setter
    private List<Registration> studentRegistrations;

    public Student(Long id , String name) {
        this.id = id;
        this.name = name;
    }

    public Registration getRegistrationByCourse(Course course) {
        if (isEnrolledToCourse(course))
            return this.studentRegistrations.stream()
                .filter(registration -> registration.getCourse() == course)
                .findFirst()
                    .get();
        else
            throw new CourseNotFoundException("Student not enrolled to course");
    }

    public boolean isEnrolledToCourse(Course course) {
        boolean courseExists =
                this.studentRegistrations.stream()
                        .anyMatch(registration -> registration.getCourse() == course);
        return courseExists;
    }
}
