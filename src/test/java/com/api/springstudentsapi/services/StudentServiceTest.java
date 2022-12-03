package com.api.springstudentsapi.services;

import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.exceptions.student.StudentNotFoundException;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import com.api.springstudentsapi.repositories.StudentRepository;
import org.junit.jupiter.api.*;
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
class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;
    StudentService classUnderTest;
    RegistrationRepository registrationRepository;

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
    void shouldGetStudentById() {
        // Given
        Student student = new Student(1L,"Archo");

        given(studentRepository.findById(any(Long.class)))
                .willReturn(Optional.of(student));

        // When
        Student resultStudent = classUnderTest.getStudentById(1L);

        // Then
        assertThat(resultStudent.getId()).isEqualTo(student.getId());
    }

    @Test
    void shouldThrowWhenNotFoundInGetStudentId() {
        // Given
        Student student = new Student(1L,"Archo");

        given(studentRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(() -> classUnderTest.getStudentById(1L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student not found.");
    }

    @Test
    void shouldAddStudent() {
        // Given
        Student student = new Student(1L, "Archontis");

        // When
        classUnderTest.addStudent(student);

        // Then
        ArgumentCaptor<Student> studentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository)
                .save(studentCaptor.capture());

        Student capturedStudent = studentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void deleteStudentById() {
        // Given
        Student student = new Student(1L, "Archo");

        given(studentRepository.findById(any(Long.class)))
                .willReturn(Optional.of(student));
        // When
        classUnderTest.deleteStudentById(1L);

        // Then
        ArgumentCaptor<Student> studentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository)
                .delete(studentCaptor.capture());

        Student capturedStudent = studentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void shouldTrowOnDeleteStudentById() {
        // Given
        Student student = new Student(1L, "Archo");

        given(studentRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(() -> classUnderTest.deleteStudentById(1L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student not found.");
    }

    @Test
    void updateStudentById() {
        // Given
        Student student = new Student(1L, "Archo");
        String newName = "George";

        given(studentRepository.findById(any(Long.class)))
                .willReturn(Optional.of(student));

        // When
        classUnderTest.updateStudentById(1L, newName);

        // Then
        ArgumentCaptor<Long> givenIdCaptor =
                ArgumentCaptor.forClass(Long.class);

        ArgumentCaptor<Student> studentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository)
                .findById(givenIdCaptor.capture());

        verify(studentRepository)
                .save(studentCaptor.capture());

        // Make sure we find the student with the correct id
        Long capturedId = givenIdCaptor.getValue();
        assertThat(capturedId).isEqualTo(student.getId());
        // Make sure that the name is updated when we save to the database
        Student capturedStudent = studentCaptor.getValue();
        assertThat(newName).isEqualTo(capturedStudent.getName());
    }

    @Test
    void shouldThrowWhenStudentNotFoundInUpdate(){
        // Given
        Student student = new Student(1L, "Archo");

        given(studentRepository.findById(any(Long.class)))
                .willReturn(Optional.empty());

        // Then
        assertThatThrownBy(() -> classUnderTest.updateStudentById(1L, "new name"))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student not found.");
    }
}