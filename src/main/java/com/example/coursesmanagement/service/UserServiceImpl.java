package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.UserDto;
import com.example.coursesmanagement.model.entity.UserEntity;
import com.example.coursesmanagement.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDto saveUser(UserDto userDto) {
        return UserDto.from(userJpaRepository.save(UserEntity.toNewEntity(userDto)));
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userJpaRepository.findAll().stream()
                .map(user -> UserDto.from(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return UserDto.from(userJpaRepository.findById(userDto.getId())
                .map(userEntity -> {
                    userEntity.setLogin(userDto.getLogin());
                    userEntity.setPassword(userDto.getPassword());
                    userEntity.setUserType((userDto.getUserType()));
                    userEntity.setName(userDto.getName());
                    userEntity.setSurname(userDto.getSurname());
                    userEntity.setActiveUser(userDto.getActiveUser());
                    return userEntity;
                }).orElseThrow(() -> new EntityNotFoundException(UserEntity.class, userDto.getId())));
    }

    @Override
    public UserDto findUserById(Long id) {
        return UserDto.from(userJpaRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException(UserEntity.class, id)
        ));
    }


}
