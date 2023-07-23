package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.CourseDto;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.repository.CourseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseJpaRepository courseJpaRepository;

    @Override
    public CourseDto saveCourse(CourseDto courseDto) {
        return CourseDto.from(courseJpaRepository.save(CourseEntity.toNewEntity(courseDto)));
    }

    @Override
    public CourseDto findCourseById(Long id) {
        return CourseDto.from(courseJpaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(CourseEntity.class, id)));
    }


}
