package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "student_takes_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> enrolledStudents = new HashSet<>();

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
    public Set<Student> getEnrolledStudents() {return enrolledStudents;}
    public void setEnrolledStudents(Set<Student> enrolledStudents) {this.enrolledStudents = enrolledStudents;}

    public void enrollStudent(Student student) {
        this.enrolledStudents.add(student);
    }
}
