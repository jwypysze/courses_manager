package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<CourseEntity,Long> {
}
