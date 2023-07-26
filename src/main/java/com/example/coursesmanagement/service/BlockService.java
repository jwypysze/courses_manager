package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.BlockDto;
import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.repository.BlockJpaRepository;
import com.example.coursesmanagement.repository.CourseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockJpaRepository blockJpaRepository;
    private final CourseJpaRepository courseJpaRepository;

    public void addBlock(BlockDto blockDto) {
        Optional<CourseEntity> courseById =
                courseJpaRepository.findById(blockDto.getCourseId());
        CourseEntity courseEntity = courseById.orElseThrow(() ->
                new EntityNotFoundException(CourseEntity.class, blockDto.getCourseId()));
        BlockEntity  blockEntity= new BlockEntity(blockDto.getBlockTitle(), courseEntity);
        blockJpaRepository.save(blockEntity);
    }

}
