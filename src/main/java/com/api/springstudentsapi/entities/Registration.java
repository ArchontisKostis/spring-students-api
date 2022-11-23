package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {
    @Id
    @GeneratedValue
    @Getter @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    @Getter @Setter
    private Course course;

    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    @Getter @Setter
    private Student student;

    @Getter @Setter
    private int grade;

    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "Course: " + course.getName() +
                " Grade: " + grade +
                '}';
    }
}
