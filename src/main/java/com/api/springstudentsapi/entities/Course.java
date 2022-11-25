package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    @Getter @Setter
    private Set<Registration> courseRegistrations;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    @Getter @Setter
    private Set<Registration> courseTeachings;
    
    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
