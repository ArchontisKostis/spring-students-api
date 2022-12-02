package com.api.springstudentsapi.repositories;

import com.api.springstudentsapi.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository
        extends JpaRepository<Registration, Long> {

}
