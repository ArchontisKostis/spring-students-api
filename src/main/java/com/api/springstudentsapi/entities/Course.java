package com.api.springstudentsapi.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

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
    public void setName(String name) {
        this.name = name;
    }
}
