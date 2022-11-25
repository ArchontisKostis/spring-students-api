package com.api.springstudentsapi.repositories;

import com.api.springstudentsapi.entities.Teaching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingRepository
        extends JpaRepository<Teaching, Long> {
}
