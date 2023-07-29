package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.CourseDto;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.repository.CourseJpaRepository;
import com.example.coursesmanagement.repository.RegistrationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationJpaRepository registrationJpaRepository;
    private final CourseJpaRepository courseJpaRepository;

    public List<Long> findRegistrationsByCourse(CourseEntity courseEntity) {
        List<Long> registrationsIdByCourse =
                registrationJpaRepository.findRegistrationsByCourse(courseEntity);
        return registrationsIdByCourse;
    }
}
