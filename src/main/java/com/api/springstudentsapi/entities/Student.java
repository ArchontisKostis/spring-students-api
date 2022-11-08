package com.api.springstudentsapi.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "enrolledStudents")
    private Set<Course> courses = new HashSet<>();

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
    public Set<Course> getCourses() {return courses;}
    public void setCourses(Set<Course> courses) {this.courses = courses;}

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
