package com.example.coursesmanagement.service;

import com.example.coursesmanagement.model.dto.UserDto;
import com.example.coursesmanagement.model.entity.UserEntity;
import com.example.coursesmanagement.repository.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDto saveUser(UserDto userDto) {
        return UserDto.from(userJpaRepository.save(UserEntity.toNewEntity(userDto)));
    }
}
