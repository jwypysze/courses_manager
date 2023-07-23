package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationJpaRepository extends JpaRepository<RegistrationEntity,Long> {
}
