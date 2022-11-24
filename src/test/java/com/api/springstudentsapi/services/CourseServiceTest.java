package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.exceptions.course.CourseNotFoundException;
import com.api.springstudentsapi.repositories.CourseRepository;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    CourseService classUnderTest;
    RegistrationRepository registrationRepository;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("----- Test " + testInfo.getDisplayName() + " Started -----");
        classUnderTest = new CourseService(courseRepository, registrationRepository);
    }

    @AfterEach
    void tearDown() {
        System.out.println("---- Test Completed ----" + System.lineSeparator());
    }

    @Test
    void shouldGetAllCourses() {
        // When
        classUnderTest.getAllCourses();
        // Then
        verify(courseRepository).findAll();
    }

    @Test
    void shouldAddCourse() {
        // Given
        Course course = new Course(1L, "Maths");

        // When
        classUnderTest.addCourse(course);

        // Then
        ArgumentCaptor<Course> courseCaptor =
                ArgumentCaptor.forClass(Course.class);

         // Capture argument
        verify(courseRepository)
                .save(courseCaptor.capture());

        Course capturedCourse = courseCaptor.getValue();
        assertThat(capturedCourse).isEqualTo(course);
    }

    @Test
    void shouldFindCourseById() {
        // Given
        Course course = new Course(1L, "Maths");
        courseRepository.save(course);

        given(courseRepository.findById(any(Long.class)))
                .willReturn(Optional.of(course));

        // When
        Course foundCourse = classUnderTest.getCourseById(1L);

        // Then
        assertThat(foundCourse).isEqualTo(course);
    }

    @Test
    void shouldThrowOnFindCourseById() {
        // Given
        Course course = new Course(1L, "Maths");
        courseRepository.save(course);

        given(courseRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        // Then
        assertThatThrownBy(() -> classUnderTest.getCourseById(1L))
                .isInstanceOf(CourseNotFoundException.class)
                .hasMessageContaining("Course not found.");

    }

    @Test
    void shouldDeleteCourseById() {
        // Given
        Course course = new Course(1L, "Spring Boot");

        given(courseRepository.findById(any(Long.class)))
                .willReturn(Optional.of(course));

        // Whem
        classUnderTest.deleteCourse(1L);

        // Then
        ArgumentCaptor<Course> courseCaptor =
                ArgumentCaptor.forClass(Course.class);

        verify(courseRepository)
                .delete(courseCaptor.capture());

        Course capturedCourse = courseCaptor.getValue();

        assertThat(capturedCourse).isEqualTo(course);
    }

    @Test
    void shouldThrowOnDeleteStudentById() {
        // Given
        Course course = new Course(1L, "Spring Boot");

        given(courseRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        // Whem
        // Then
        assertThatThrownBy(() -> classUnderTest.deleteCourse(1L))
                .isInstanceOf(CourseNotFoundException.class)
                .hasMessageContaining("Course not found.");
    }

    @Test
    void shouldUpdateCourseById() {
        // Given
        Course courseToUpdate = new Course(1L, "KAVE");
        String newName = "JAVA";

        given(courseRepository.findById(any(Long.class)))
                .willReturn(Optional.of(courseToUpdate));

        // When
        classUnderTest.updateCourseById(1L, newName);

        // Then
        ArgumentCaptor<Long> courseIdCaptor =
                ArgumentCaptor.forClass(Long.class);

        ArgumentCaptor<Course> courseCaptor =
                ArgumentCaptor.forClass(Course.class);

        verify(courseRepository)
                .findById(courseIdCaptor.capture());

        verify(courseRepository)
                .save(courseCaptor.capture());

        // Make sure we find the correct student
        Long capturedId = courseIdCaptor.getValue();
        assertThat(capturedId).isEqualTo(courseToUpdate.getId());
        // Make sure that the name is update when we save
        Course capturedCourse = courseCaptor.getValue();
        assertThat(newName).isEqualTo(courseToUpdate.getName());
    }
}