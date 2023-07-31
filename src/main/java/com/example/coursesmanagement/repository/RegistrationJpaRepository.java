package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.model.entity.RegistrationEntity;
import com.example.coursesmanagement.model.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationJpaRepository extends JpaRepository<RegistrationEntity,Long> {

    @Query
            (value = "SELECT r.id FROM RegistrationEntity r INNER JOIN r.courseEntity c WHERE r.courseEntity = :courseEntity",
                    nativeQuery = false)
    List<Long> findRegistrationsByCourse(@Param("courseEntity") CourseEntity courseEntity);

    @Query
            (value = "SELECT r.id FROM RegistrationEntity r INNER JOIN r.userEntity u WHERE r.userEntity = :userEntity",
                    nativeQuery = false)
    List<Long> findRegistrationsByUser(@Param("userEntity") UserEntity userEntity);

    @Query
            (value = "UPDATE registrations r SET r.user_id = :newUserId, r.course_id = :newCourseId WHERE r.id = :registrationId",
                    nativeQuery = true)
    @Modifying
    @Transactional
    void updateRegistrationById(@Param("newUserId") Long newUserId,
                         @Param("newCourseId") Long newCourseId,
                         @Param("registrationId") Long registrationId);
}
