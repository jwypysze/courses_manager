package com.example.coursesmanagement.service;

import com.example.coursesmanagement.model.dto.RegistrationDto;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.model.entity.RegistrationEntity;
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

    public List<RegistrationDto> getAllRegistrations() {
        List<RegistrationEntity> all = registrationJpaRepository.findAll();
        List<RegistrationDto> registrations = all.stream()
                .map(registrationEntity ->
                        new RegistrationDto(registrationEntity.getId(),
                                registrationEntity.getDate(), registrationEntity.getTime(),
                                registrationEntity.getUserEntity().getName(),
                                registrationEntity.getUserEntity().getSurname(),
                                registrationEntity.getCourseEntity().getTitle()))
                .toList();
        return registrations;
    }
}
