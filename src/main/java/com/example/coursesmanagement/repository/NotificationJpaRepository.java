package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.ClassEntity;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.model.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity,Long> {

    @Query
            (value = "SELECT n.id FROM NotificationEntity n INNER JOIN n.classEntity c WHERE n.classEntity = :classEntity",
                    nativeQuery = false)
    List<Long> findNotificationsByClass(@Param("classEntity") ClassEntity classEntity);

}
