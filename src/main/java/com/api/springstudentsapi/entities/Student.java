package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    @Getter @Setter
    private List<Registration> studentRegistrations;

    public Student(Long id , String name) {
        this.id = id;
        this.name = name;
    }
}
