package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Stream;

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
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "enrolledStudents")
    @JsonManagedReference
    private Collection<Course> courses = new LinkedList<>();

    public Student(String name) {
        this.name = name;
    }

    public Student() {}

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // GETTERS - SETTERS
    // id
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    // name
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    // courses
    public Collection<Course> getCourses() {return courses;}
    public void setCourses(Set<Course> courses) {this.courses = courses;}

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
