package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.dto.CourseDto;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.model.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
