package com.api.springstudentsapi.dto;

import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    @Getter @Setter
    private Student student;
    @Getter @Setter
    private EnrolledCourseDTO course;

    public static RegistrationDTO convert(Registration registration) {
        Student registrationStudent = registration.getStudent();
        EnrolledCourseDTO courseDTO = EnrolledCourseDTO.convert(registration);

        return new RegistrationDTO(registrationStudent, courseDTO);
    }
}
