package com.api.springstudentsapi.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public Student() {}

    // GETTERS - SETTERS
    // id
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    // name
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
