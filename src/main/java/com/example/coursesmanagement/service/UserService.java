package com.example.coursesmanagement.service;

import com.example.coursesmanagement.model.dto.UserDto;
import com.example.coursesmanagement.model.entity.UserEntity;
import com.example.coursesmanagement.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

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
}
