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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final ClassJpaRepository classJpaRepository;
    private final BlockJpaRepository blockJpaRepository;
    public void addClass(ClassDto classDto) {
        BlockEntity blockEntity = blockJpaRepository.findById(classDto.getBlockEntity().getId())
                .orElseThrow(() -> new EntityNotFoundException
                        (BlockEntity.class, classDto.getBlockEntity().getId()));
        ClassEntity classEntity = new ClassEntity(classDto.getTopic(),
                classDto.getDate(), classDto.getTime(), blockEntity);
        classJpaRepository.save(classEntity);
    }
}
