package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.BlockDto;
import com.example.coursesmanagement.model.dto.CourseDto;
import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.ClassEntity;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.repository.BlockJpaRepository;
import com.example.coursesmanagement.repository.ClassJpaRepository;
import com.example.coursesmanagement.repository.CourseJpaRepository;
import com.example.coursesmanagement.repository.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockJpaRepository blockJpaRepository;
    private final CourseJpaRepository courseJpaRepository;
    private final ClassJpaRepository classJpaRepository;
    private final NotificationJpaRepository notificationJpaRepository;

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
                blockEntity.getCourseEntity().getTitle());
    }

    public void deleteBlockById(BlockDto blockDto) {
        BlockEntity blockEntity =
                blockJpaRepository.findById(blockDto.getId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(BlockEntity.class, blockDto.getId()));
        List<Long> classesIdByBlock = classJpaRepository.findClassesByBlock(blockEntity);
        for(Long classId : classesIdByBlock) {
            ClassEntity classEntity = classJpaRepository.findById(classId)
                    .orElseThrow(() -> new EntityNotFoundException(ClassEditor.class, classId));
            List<Long> notificationsIdByClass =
                    notificationJpaRepository.findNotificationsByClass(classEntity);
            for(Long notificationId : notificationsIdByClass) {
                notificationJpaRepository.deleteNotificationsFromTableUsersNotifications(notificationId);
            }
            notificationsIdByClass
                    .forEach(notificationJpaRepository::deleteById);
        }
        classesIdByBlock
                .forEach(classJpaRepository::deleteById);
        blockJpaRepository.delete(blockEntity);
    }

    public List<BlockDto> findBlocksInCourse(CourseDto courseDto) {
        List<Long> blocksIdInCourse = blockJpaRepository.findBlocksIdInCourse(courseDto.getId());
        List<BlockEntity> blocksEntitiesInCourse = new ArrayList<>();
        for(Long blockId : blocksIdInCourse) {
            BlockEntity blockEntity = blockJpaRepository.findById(blockId)
                    .orElseThrow(() -> new EntityNotFoundException(BlockEntity.class, blockId));
            blocksEntitiesInCourse.add(blockEntity);
        }
        return blocksEntitiesInCourse.stream()
                .map(blockEntity -> new BlockDto(blockEntity.getId(), blockEntity.getBlockTitle(),
                        blockEntity.getCourseEntity().getTitle())).toList();
    }

    public void updateBlockById(BlockDto blockDto) {
        BlockEntity blockEntity = blockJpaRepository.findById(blockDto.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(BlockEntity.class, blockDto.getId()));
        blockJpaRepository.updateBlockById(blockDto.getBlockTitle(),
                blockDto.getCourseId(), blockEntity.getId());
    }

    public BlockDto findBlockById(Long id) {
        BlockEntity blockEntity = blockJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BlockEntity.class, id));
        return new BlockDto(blockEntity.getId(), blockEntity.getBlockTitle(), blockEntity.getCourseEntity().getTitle());
    }
}
