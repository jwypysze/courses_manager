package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassJpaRepository extends JpaRepository<ClassEntity,Long> {
}
