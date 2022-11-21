package com.api.springstudentsapi.entities;

import lombok.*;
import javax.persistence.*;

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
}
