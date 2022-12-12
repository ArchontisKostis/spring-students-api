package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.repositories.CourseRepository;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.api.springstudentsapi.repositories.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @Mock
    RegistrationRepository registrationRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    CourseRepository courseRepository;
    RegistrationService classUnderTest;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("----- Test " + testInfo.getDisplayName() + " Started -----");
        classUnderTest = new RegistrationService(registrationRepository, new StudentService(studentRepository), new CourseService(courseRepository));
    }

    @AfterEach
    void tearDown() {
        System.out.println("---- Test Completed ----" + System.lineSeparator());
    }

    @Test
    void shouldGetAllRegistrations() {
        // When
        classUnderTest.getRegistrations();
        // Then
        verify(registrationRepository).findAll();
    }

    @Test
    void shouldRegisterStudentToCourse() {
        // Given
        Student givenStudent = new Student(1L, "Archo");
        Course givenCourse = new Course(1L, "Web Dev");

        given(studentRepository.findById(any(Long.class)))
                .willReturn(Optional.of(givenStudent));

        given(courseRepository.findById(any(Long.class)))
                .willReturn(Optional.of(givenCourse));

        // When
        classUnderTest.registerStudentToCourse(1L, 1L);

        // Then
        // Capture Saved Registration
        ArgumentCaptor<Registration> registrationCaptor =
                ArgumentCaptor.forClass(Registration.class);


        verify(registrationRepository)
                .save(registrationCaptor.capture());

        Registration capturedRegistration = registrationCaptor.getValue();
        Student registrationStudent = capturedRegistration.getStudent();
        Course registrationCourse = capturedRegistration.getCourse();

        // Make sure the saved registration has the givenStudent and givenCourse
        assertThat(registrationCourse).isEqualTo(givenCourse);
        assertThat(registrationStudent).isEqualTo(givenStudent);
    }
}