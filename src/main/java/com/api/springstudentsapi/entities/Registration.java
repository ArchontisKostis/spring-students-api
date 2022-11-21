package com.api.springstudentsapi.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Registration {
    @Id
    @Getter @Setter
    private Long id;

    @ManyToOne
    @Getter @Setter
    private Student student;

    @ManyToOne
    @Getter @Setter
    private Course course;

    @Getter @Setter
    private Long grade;
}
