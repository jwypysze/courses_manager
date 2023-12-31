package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.BlockDto;
import com.example.coursesmanagement.model.dto.ClassDto;
import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.ClassEntity;
import com.example.coursesmanagement.repository.BlockJpaRepository;
import com.example.coursesmanagement.repository.ClassJpaRepository;
import com.example.coursesmanagement.repository.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassJpaRepository classJpaRepository;
    private final BlockJpaRepository blockJpaRepository;
    private final NotificationJpaRepository notificationJpaRepository;
    public void addClass(ClassDto classDto) {
        BlockEntity blockEntity = blockJpaRepository.findById(classDto.getBlockId())
                .orElseThrow(() -> new EntityNotFoundException
                        (BlockEntity.class, classDto.getBlockId()));
        ClassEntity classEntity = new ClassEntity(classDto.getTopic(),
                classDto.getDate(), classDto.getTime(), blockEntity);
        classJpaRepository.save(classEntity);
    }

    public List<ClassDto> getAllClasses() {
        return classJpaRepository.findAll().stream()
                .map(classEntity ->
                        new ClassDto(classEntity.getId(), classEntity.getTopic(),
                                classEntity.getDate(), classEntity.getTime(),
                                classEntity.getBlockEntity().getBlockTitle(),
                                classEntity.getBlockEntity().getCourseEntity().getTitle()))
                .toList();
    }

    public void deleteClassById(ClassDto classDto) {
        ClassEntity classEntity = classJpaRepository.findById(classDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(ClassEntity.class, classDto.getId()));
        List<Long> notificationsByClass =
                notificationJpaRepository.findNotificationsByClass(classEntity);
        for(Long notificationId : notificationsByClass) {
            notificationJpaRepository.deleteNotificationsFromTableUsersNotifications(notificationId);
        }
        notificationsByClass
                        .forEach(notificationJpaRepository::deleteById);
        classJpaRepository.delete(classEntity);
    }

    public List<ClassDto> findClassesInBlock(BlockDto blockDto) {
        List<Long> classesIdInBlock = classJpaRepository.findClassesIdInBlock(blockDto.getId());
        List<ClassEntity> classEntitiesInBlock = new ArrayList<>();
        for(Long classId : classesIdInBlock) {
            ClassEntity classEntity = classJpaRepository.findById(classId)
                    .orElseThrow(() -> new EntityNotFoundException(ClassEntity.class, classId));
            classEntitiesInBlock.add(classEntity);
        }
        return classEntitiesInBlock.stream()
                .map(classEntity -> new ClassDto(classEntity.getId(), classEntity.getTopic(),
                        classEntity.getDate(), classEntity.getTime(),
                        classEntity.getBlockEntity().getBlockTitle(), classEntity.getBlockEntity().getCourseEntity().getTitle())).toList();
    }

    public void updateClassById(ClassDto classDto) {
        ClassEntity classEntity = classJpaRepository.findById(classDto.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(ClassEntity.class, classDto.getId()));
        classJpaRepository.updateClassById
                (classDto.getTopic(), classDto.getDate(), classDto.getTime(),
                        classDto.getBlockId(), classEntity.getId());
    }

    public ClassDto findClassById(Long id) {
        ClassEntity classEntity = classJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ClassEntity.class, id));
        return new ClassDto(classEntity.getId(), classEntity.getTopic(), classEntity.getDate(),
                classEntity.getTime(), classEntity.getBlockEntity().getBlockTitle(),
                classEntity.getBlockEntity().getCourseEntity().getTitle());
    }
}
