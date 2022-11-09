package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity

public class Course {
    @Id
    @SequenceGenerator(
            name = "courseIdGenerator",
            sequenceName = "courseSequennce",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courseIdGenerator"
    )
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_takes_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonBackReference
    private Collection<Student> enrolledStudents = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "teacher_teaches_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @JsonBackReference
    private Collection<Teacher> teachers = new LinkedList<>();

    public Course(long l, String maths) {
    }

    public Course() {

    }

    // GETTERS - SETTERS
    // id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // name
    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name;}

    // enrolledStudents
    public Collection<Student> getEnrolledStudents() {return enrolledStudents;}
    public void setEnrolledStudents(Collection<Student> enrolledStudents) {this.enrolledStudents = enrolledStudents;}

    // teachers


    public Collection<Teacher> getTeachers() {return teachers;}

    public void setTeachers(Collection<Teacher> teachers) {this.teachers = teachers;}

    public void enrollStudent(Student student) {
        this.enrolledStudents.add(student);
    }
}
