package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @Mock
    RegistrationRepository registrationRepository;
    RegistrationService classUnderTest;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("----- Test " + testInfo.getDisplayName() + " Started -----");
        classUnderTest = new RegistrationService(registrationRepository);
    }

    @AfterEach
    void tearDown() {
        System.out.println("---- Test Completed ----" + System.lineSeparator());
    }

    @Test
    void registerStudentToCourse() {
        // Given
        Student givenStudent = new Student(1L, "Archo");
        Course givenCourse = new Course(1L, "Web Dev");

        // When
        classUnderTest.registerStudentToCourse(givenStudent, givenCourse);

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