package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockJpaRepository extends JpaRepository<BlockEntity,Long> {

    @Query
            (value = "SELECT b.id FROM BlockEntity b INNER JOIN b.courseEntity c WHERE b.courseEntity = :courseEntity",
                    nativeQuery = false)
    List<Long> findBlocksByCourse(@Param("courseEntity") CourseEntity courseEntity);
}
