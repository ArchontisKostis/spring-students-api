package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.repositories.StudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;
    StudentService classUnderTest;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("----- Test " + testInfo.getDisplayName() + " Started -----");
        classUnderTest = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("---- Test Completed ----" + System.lineSeparator());
    }

    @Test
    void shouldGetAllStudents() {
        // When
        classUnderTest.getAllStudents();
        // Then
        verify(studentRepository).findAll();
    }

    @Test
    void getStudentById() {
        // Given
        Student student = new Student(1L,"Archo");
        studentRepository.save(student);

        // When
        Student resultStudent = classUnderTest.getStudentById(1L);

        // Then
        assertThat(resultStudent.getId()).isEqualTo(student.getId());
    }

    @Test
    void shouldAddStudent() {
        // Given
        Student student = new Student("Archontis");

        // When
        classUnderTest.addStudent(student);

        // Then
        ArgumentCaptor<Student> studentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository)
                .save(studentCaptor.capture());

        Student capturedStudent = studentCaptor.getValue();
        assertThat(student).isEqualTo(capturedStudent);
    }

    @Test
    void deleteStudentById() {
        // Given
        Student student = new Student(1L, "Archo");

        given(studentRepository.existsById(any(Long.class)))
                .willReturn(true);
        // When
        classUnderTest.deleteStudentById(1L);

        // Then
        ArgumentCaptor<Long> studentIdCaptor =
                ArgumentCaptor.forClass(Long.class);

        verify(studentRepository)
                .deleteById(studentIdCaptor.capture());

        Long capturedId = studentIdCaptor.getValue();
        assertThat(capturedId).isEqualTo(student.getId());
    }

    @Test
    @Disabled
    void updateStudentById() {
    }
}