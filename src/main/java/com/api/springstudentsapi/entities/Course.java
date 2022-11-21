package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;

    @OneToMany(targetEntity = Registration.class, mappedBy = "student")
    @Getter @Setter
    private Collection<Registration> registrations = new LinkedList<>();

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addRegistration(Registration registration){
        this.registrations.add(registration);
    }
}
