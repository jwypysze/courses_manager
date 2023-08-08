package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.RegistrationDto;
import com.example.coursesmanagement.model.dto.UserDto;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.model.entity.RegistrationEntity;
import com.example.coursesmanagement.model.entity.UserEntity;
import com.example.coursesmanagement.repository.CourseJpaRepository;
import com.example.coursesmanagement.repository.RegistrationJpaRepository;
import com.example.coursesmanagement.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationJpaRepository registrationJpaRepository;
    private final CourseJpaRepository courseJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public List<Long> findRegistrationsIdByCourse(CourseEntity courseEntity) {
        return registrationJpaRepository.findRegistrationsByCourse(courseEntity);
    }

    public List<RegistrationDto> getAllRegistrations() {
        List<RegistrationEntity> all = registrationJpaRepository.findAll();
        return all.stream()
                .map(registrationEntity ->
                        new RegistrationDto(registrationEntity.getId(),
                                registrationEntity.getDate(), registrationEntity.getTime(),
                                registrationEntity.getUserEntity().getName(),
                                registrationEntity.getUserEntity().getSurname(),
                                registrationEntity.getCourseEntity().getTitle()))
                .toList();
    }

    public void addRegistration(RegistrationDto registrationDto) {
        CourseEntity courseEntity = courseJpaRepository.findById(registrationDto.getCourseId())
                .orElseThrow(() ->
                new EntityNotFoundException(CourseEntity.class, registrationDto.getCourseId()));
        UserEntity userEntity = userJpaRepository.findById(registrationDto.getUserId())
                .orElseThrow(() ->
                        new EntityNotFoundException(UserEntity.class, registrationDto.getUserId()));
        RegistrationEntity registrationEntity = new RegistrationEntity(userEntity, courseEntity);
        registrationJpaRepository.save(registrationEntity);
    }

    public void deleteRegistrationById(RegistrationDto registrationDto) {
        RegistrationEntity registrationEntity = registrationJpaRepository.findById(registrationDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(RegistrationEntity.class, registrationDto.getId()));
        registrationJpaRepository.delete(registrationEntity);
    }

    public void updateRegistrationById(RegistrationDto registrationDto) {
        RegistrationEntity registrationEntity = registrationJpaRepository.findById(registrationDto.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException(RegistrationEntity.class, registrationDto.getId()));
        registrationJpaRepository.updateRegistrationById
                (registrationDto.getUserId(), registrationDto.getCourseId(), registrationEntity.getId());
    }

    public void addRegistrationByUser(RegistrationDto registrationDto) {
        CourseEntity courseEntity = courseJpaRepository.findById(registrationDto.getCourseId())
                .orElseThrow(() ->
                        new EntityNotFoundException(CourseEntity.class, registrationDto.getCourseId()));
        UserEntity userEntity = userJpaRepository.findUserByNameAndSurname
                        (registrationDto.getUserName(), registrationDto.getUserSurname())
                .orElseThrow(() ->
                        new jakarta.persistence.EntityNotFoundException
                                ("User with provided name and surname doesn't exist!"));
        RegistrationEntity registrationEntity = new RegistrationEntity(userEntity, courseEntity);
        registrationJpaRepository.save(registrationEntity);
    }

    public List<RegistrationDto> findStudentRegistrations(UserDto userDto) {
        UserEntity userEntity = userJpaRepository.findUserByNameAndSurname
                        (userDto.getName(), userDto.getSurname())
                .orElseThrow(() ->
                        new jakarta.persistence.EntityNotFoundException
                                ("User with provided name and surname doesn't exist!"));
        List<Long> registrationsIdByUser = registrationJpaRepository.findRegistrationsIdByUser(userEntity);
        List<RegistrationEntity> registrationEntities = new ArrayList<>();
        for (Long registrationId : registrationsIdByUser) {
            RegistrationEntity registrationEntity = registrationJpaRepository.findById(registrationId)
                    .orElseThrow(() -> new EntityNotFoundException(RegistrationEntity.class, registrationId));
            registrationEntities.add(registrationEntity);
        }
        return registrationEntities.stream()
                .map(registrationEntity -> new RegistrationDto
                        (registrationEntity.getId(), registrationEntity.getDate(),
                                registrationEntity.getTime(),
                                registrationEntity.getUserEntity().getName(),
                                registrationEntity.getUserEntity().getSurname(),
                                registrationEntity.getCourseEntity().getTitle()))
                .toList();
    }
}
