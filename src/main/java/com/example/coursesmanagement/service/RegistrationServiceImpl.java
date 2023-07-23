package com.example.coursesmanagement.service;

import com.example.coursesmanagement.model.dto.RegistrationDto;
import com.example.coursesmanagement.model.entity.RegistrationEntity;
import com.example.coursesmanagement.repository.CourseJpaRepository;
import com.example.coursesmanagement.repository.RegistrationJpaRepository;
import com.example.coursesmanagement.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationJpaRepository registrationJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final CourseJpaRepository courseJpaRepository;


    @Override
    public RegistrationDto saveRegistration(RegistrationDto registrationDto) {
        RegistrationDto.from(registrationJpaRepository.save(RegistrationEntity.toNewEntity(registrationDto)));
        return null;
    }
}
