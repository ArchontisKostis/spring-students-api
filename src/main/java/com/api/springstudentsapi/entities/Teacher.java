package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "teachers")
    @JsonManagedReference
    private Collection<Course> teachingCourses = new LinkedList<>();


    public Teacher(long id, String name) {
    }

    public Teacher() {

    }


    // GETTERS - SETTERS
    // id
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    // name
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    // courses
    public Collection<Course> getTeachingCourses() {return teachingCourses;}
    public void setTeachingCourses(Collection<Course> teachingCourses) {this.teachingCourses = teachingCourses;}
}
