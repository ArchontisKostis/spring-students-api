package com.api.springstudentsapi.entities;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teaching {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    @Getter @Setter
    private Course course;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable=false)
    @Getter @Setter
    private Teacher teacher;

    public Teaching(Teacher teacher, Course course) {
        this.teacher = teacher;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Teaching{" +
                "Course: " + course.getName() +
                '}';
    }
}
