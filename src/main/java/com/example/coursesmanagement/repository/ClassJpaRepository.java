package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.ClassEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ClassJpaRepository extends JpaRepository<ClassEntity,Long> {

    @Query
            (value = "SELECT c.id FROM ClassEntity c INNER JOIN c.blockEntity b WHERE c.blockEntity = :blockEntity",
                    nativeQuery = false)
    List<Long> findClassesByBlock(@Param("blockEntity") BlockEntity BlockEntity);

    @Query
            (value = "SELECT c.id FROM classes c INNER JOIN blocks b ON c.block_id = b.id WHERE c.block_id = :blockId",
                    nativeQuery = true)
    List<Long> findClassesIdInBlock(@Param("blockId") Long blockId);

    @Query
            (value = "UPDATE classes c SET c.topic = :newTopic, c.date = :newDate, c.time = :newTime, c.block_id = :newBlockId WHERE c.id = :classId",
                    nativeQuery = true)
    @Modifying
    @Transactional
    void updateClassById(@Param("newTopic") String newTopic,
                         @Param("newDate") LocalDate newDate,
                         @Param("newTime") LocalTime newTime,
                         @Param("newBlockId") Long newBlockId,
                         @Param("classId") Long classId);
}
