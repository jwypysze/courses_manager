package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
}
