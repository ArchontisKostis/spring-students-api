package com.api.springstudentsapi.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

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
    @Column(name = "student_id")
    private Long id;
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "student")
    @Getter @Setter
    private Set<Registration> registrations;

    public Student(Long id , String name) {
        this.id = id;
        this.name = name;
    }
}
