package com.api.springstudentsapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
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
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    @Getter @Setter
    private Set<Teaching> teacherTeachings;

    public Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
