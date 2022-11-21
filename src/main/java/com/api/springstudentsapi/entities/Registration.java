package com.api.springstudentsapi.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@ToString
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
    @Getter @Setter
    private Course course;

    @ManyToOne
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
                "id=" + id +
                ", course=" + course +
                ", grade=" + grade +
                '}';
    }
}
