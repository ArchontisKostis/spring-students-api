package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Teacher;
import com.api.springstudentsapi.entities.Teaching;
import com.api.springstudentsapi.repositories.TeachingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TeachingServiceTest {
    @Mock
    TeachingRepository teachingRepository;
    TeachingService classUnderTest;


    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("----- Test " + testInfo.getDisplayName() + " Started -----");
        classUnderTest = new TeachingService(teachingRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetTeachings() {
        // When
        classUnderTest.getTeachings();
        // Then
        verify(teachingRepository).findAll();
    }

    @Test
    void assignTeacherToCourse() {
        // Given
        Teacher givenTeacher = new Teacher(1L, "Alex");
        Course givenCourse = new Course(1L, "JAVA");

        // When
        classUnderTest.assignTeacherToCourse(givenTeacher, givenCourse);

        // Then
        // Capture saved Teaching object
        ArgumentCaptor<Teaching> teachingCaptor =
                ArgumentCaptor.forClass(Teaching.class);

        verify(teachingRepository)
                .save(teachingCaptor.capture());

        Teaching capturedTeaching = teachingCaptor.getValue();
        Teacher teachingTeacher = capturedTeaching.getTeacher();
        Course teachingCourse = capturedTeaching.getCourse();

        // Make sure the saved Teaching has the givenTeacher and givenCourse
        assertThat(teachingTeacher).isEqualTo(givenTeacher);
        assertThat(teachingCourse).isEqualTo(givenCourse);
    }
}