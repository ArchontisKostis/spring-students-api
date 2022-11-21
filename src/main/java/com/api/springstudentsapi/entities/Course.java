package com.api.springstudentsapi.entities;

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

}
