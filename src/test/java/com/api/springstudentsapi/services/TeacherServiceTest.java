package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.repositories.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @Mock
    TeacherRepository teacherRepository;
    TeacherService classUnderTest;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("----- Test " + testInfo.getDisplayName() + " Started -----");
        classUnderTest = new TeacherService(teacherRepository);
    }

    @AfterEach
    void tearDown() {
        System.out.println("---- Test Completed ----" + System.lineSeparator());
    }

    @Test
    void getAllTeachers() {
        // When
        classUnderTest.getAllTeachers();
        // Then
        verify(teacherRepository).findAll();
    }

    @Test
    void addTeacher() {
        // Given
        Teacher teacher = new Teacher(1L, "John");

        // When
        classUnderTest.addTeacher(teacher);

        // Then
        ArgumentCaptor<Teacher> teacherCaptor =
                ArgumentCaptor.forClass(Teacher.class);

        verify(teacherRepository)
                .save(teacherCaptor.capture());

        Teacher capturedTeacher = teacherCaptor.getValue();
        assertThat(capturedTeacher).isEqualTo(teacher);
    }
}