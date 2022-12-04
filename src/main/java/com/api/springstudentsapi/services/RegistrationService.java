package com.api.springstudentsapi.services;

import com.api.springstudentsapi.dto.RegistrationDTO;
import com.api.springstudentsapi.entities.Course;
import com.api.springstudentsapi.entities.Registration;
import com.api.springstudentsapi.entities.Student;
import com.api.springstudentsapi.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<RegistrationDTO> getRegistrations() {
        List<Registration> registrationList = registrationRepository.findAll();

        Stream<RegistrationDTO> registrationDTOStream =
                registrationList.stream()
                        .map(registration -> RegistrationDTO.convert(registration));

        return registrationDTOStream.collect(Collectors.toList());
    }

    public Registration registerStudentToCourse(Student student, Course course) {
        Registration registration = new Registration(student, course);
        return registrationRepository.save(registration);
    }
}
