package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.BlockDto;
import com.example.coursesmanagement.model.dto.CourseDto;
import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.repository.BlockJpaRepository;
import com.example.coursesmanagement.repository.CourseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<BlockDto> getAllBlocks() {
        return blockJpaRepository.findAll().stream()
                .map(blockEntity ->
                    new BlockDto
                            (blockEntity.getId(), blockEntity.getBlockTitle(),
                                    blockEntity.getCourseEntity().getTitle())
                ).toList();
    }

    public BlockDto getBlockById(Long blockId) {
        BlockEntity blockEntity = blockJpaRepository.findById(blockId)
                .orElseThrow(() -> new EntityNotFoundException(BlockEntity.class, blockId));
        return new BlockDto(blockEntity.getId(), blockEntity.getBlockTitle(),
                blockEntity.getCourseEntity());
    }

    public List<Long> findBlocksByCourse(CourseEntity courseEntity) {
        List<Long> blocksIdByCourse = blockJpaRepository.findBlocksByCourse(courseEntity);
        return blocksIdByCourse;
    }
}
