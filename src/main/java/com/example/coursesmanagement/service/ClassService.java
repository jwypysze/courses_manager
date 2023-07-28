package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.ClassDto;
import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.ClassEntity;
import com.example.coursesmanagement.repository.BlockJpaRepository;
import com.example.coursesmanagement.repository.ClassJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassJpaRepository classJpaRepository;
    private final BlockJpaRepository blockJpaRepository;
    public void addClass(ClassDto classDto) {
        BlockEntity blockEntity = blockJpaRepository.findById(classDto.getBlockId())
                .orElseThrow(() -> new EntityNotFoundException
                        (BlockEntity.class, classDto.getBlockEntity().getId()));
        ClassEntity classEntity = new ClassEntity(classDto.getTopic(),
                classDto.getDate(), classDto.getTime(), blockEntity);
        classJpaRepository.save(classEntity);
    }

    public List<ClassDto> getAllClasses() {
        return classJpaRepository.findAll().stream()
                .map(classEntity ->
                        new ClassDto(classEntity.getId(), classEntity.getTopic(),
                                classEntity.getDate(), classEntity.getTime(),
                                classEntity.getBlockEntity().getBlockTitle()))
                .toList();
    }

    public List<Long> findClassesByBlock(BlockEntity blockEntity) {
        return classJpaRepository.findClassesByBlock(blockEntity);
    }
}
