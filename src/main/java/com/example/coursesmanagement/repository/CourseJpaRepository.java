package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository extends JpaRepository<CourseEntity,Long> {
}
