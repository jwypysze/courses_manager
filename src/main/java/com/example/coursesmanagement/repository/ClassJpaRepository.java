package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
