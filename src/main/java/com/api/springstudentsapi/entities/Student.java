package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;

    @OneToMany(targetEntity = Registration.class, mappedBy = "course")
    @Getter @Setter
    private Collection<Registration> registrations = new LinkedList<>();

    public Student(Long id , String name) {
        this.id = id;
        this.name = name;
    }

    public void addRegistration(Registration registration) {
        this.registrations.add(registration);
    }
}
