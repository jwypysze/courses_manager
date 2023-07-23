package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockJpaRepository extends JpaRepository<BlockEntity,Long> {
}
