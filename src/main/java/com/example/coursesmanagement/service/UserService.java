package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.RegistrationDto;
import com.example.coursesmanagement.model.dto.UserDto;
import com.example.coursesmanagement.model.entity.RegistrationEntity;
import com.example.coursesmanagement.model.entity.UserEntity;
import com.example.coursesmanagement.repository.RegistrationJpaRepository;
import com.example.coursesmanagement.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final RegistrationJpaRepository registrationJpaRepository;

    public List<UserDto> getAllUsers() {
        List<UserDto> allUsers = userJpaRepository.findAll().stream()
                .map(userEntity ->
                        new UserDto(userEntity.getId(), userEntity.getLogin(),
                                userEntity.getPassword(), userEntity.getUserType(),
                                userEntity.getName(), userEntity.getSurname(),
                                userEntity.getActiveUser()))
                .toList();
        return allUsers;
    }


    public void addUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity
                (userDto.getLogin(), userDto.getPassword(), userDto.getUserType(),
                        userDto.getName(), userDto.getSurname(), userDto.getActiveUser());
        userJpaRepository.save(userEntity);
    }

    public void deleteUserById(UserDto userDto) {
        UserEntity userEntity =
                userJpaRepository.findById(userDto.getId())
                        .orElseThrow(() ->
                                new EntityNotFoundException(UserEntity.class, userDto.getId()));
        List<Long> registrationsIdByUser = registrationJpaRepository.findRegistrationsIdByUser(userEntity);
        for(Long registrationId : registrationsIdByUser) {
            registrationJpaRepository.deleteById(registrationId);
        }
        userJpaRepository.deleteUsersFromTableUsersNotifications(userEntity.getId());
        userJpaRepository.delete(userEntity);
    }

    public List<RegistrationDto> findRegistrationsByUser(UserDto userDto) {
        UserEntity userEntity = userJpaRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(UserEntity.class, userDto.getId()));
        List<Long> registrationsIdByUser = registrationJpaRepository.findRegistrationsIdByUser(userEntity);
        List<RegistrationEntity> registrationEntities = new ArrayList<>();
        for(Long registrationId : registrationsIdByUser) {
            RegistrationEntity registrationEntity = registrationJpaRepository.findById(registrationId)
                    .orElseThrow(() -> new EntityNotFoundException(RegistrationDto.class, registrationId));
            registrationEntities.add(registrationEntity);
        }
        return registrationEntities.stream()
                .map(registrationEntity ->
                        new RegistrationDto(registrationEntity.getId(),
                                registrationEntity.getDate(), registrationEntity.getTime(),
                                registrationEntity.getUserEntity().getId(),
                                registrationEntity.getCourseEntity().getId()))
                .toList();
    }

    public void updateUserById(UserDto userDto) {
        UserEntity userEntity = userJpaRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(UserEntity.class, userDto.getId()));
        userJpaRepository.updateUserById
                (userDto.getLogin(), userDto.getPassword(), userDto.getUserType(),
                        userDto.getName(), userDto.getSurname(), userDto.getActiveUser(),
                        userEntity.getId());
    }

    public UserDto findUserById(Long userId) {
        UserEntity userEntity = userJpaRepository
                .findById(userId).orElseThrow(() ->
                        new EntityNotFoundException(UserEntity.class, userId));
        UserDto userDto = new UserDto(userEntity.getId(), userEntity.getLogin(),
                userEntity.getPassword(), userEntity.getUserType(), userEntity.getName(),
                userEntity.getSurname(), userEntity.getActiveUser());
        return userDto;
    }

    public UserDto findUserByNameAndSurname(String name, String surname) {
        UserEntity userEntity = userJpaRepository.findUserByNameAndSurname(name, surname)
                .orElseThrow(() ->
                new jakarta.persistence.EntityNotFoundException
                        ("There is no user with the provided name and surname"));
        UserDto userDto = new UserDto(userEntity.getId(), userEntity.getLogin(),
                userEntity.getPassword(), userEntity.getUserType(),
                userEntity.getName(), userEntity.getSurname(), userEntity.getActiveUser());
        return userDto;
    }
}
