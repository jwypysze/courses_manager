package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity,Long> {
}
