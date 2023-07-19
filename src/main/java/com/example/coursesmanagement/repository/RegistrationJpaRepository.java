package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationJpaRepository extends JpaRepository<RegistrationEntity,Long> {
}
