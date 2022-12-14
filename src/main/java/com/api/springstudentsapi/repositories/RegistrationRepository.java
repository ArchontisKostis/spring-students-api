package com.api.springstudentsapi.repositories;

import com.api.springstudentsapi.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RegistrationRepository
        extends JpaRepository<Registration, Long> {

    @Query("SELECT registration FROM Registration registration WHERE registration.student.id = ?1 AND registration.course.id = ?2")
    Collection<Registration> getRegistrationByStudentAndCourseId(
            Long studentId,
            Long courseId
    );

}
