package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.CourseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository extends JpaRepository<CourseEntity,Long> {

    @Query
            (value = "UPDATE CourseEntity c SET c.title = :newTitle, c.imageName = :newImageName WHERE c.id = :courseId",
            nativeQuery = false)
    @Modifying
    @Transactional
    void updateCourseById(@Param("newTitle") String newTitle,
                          @Param("newImageName") String newImageName,
                          @Param("courseId") Long courseId);

}
