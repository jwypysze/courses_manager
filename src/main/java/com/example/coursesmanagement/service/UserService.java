package com.example.coursesmanagement.service;

import com.example.coursesmanagement.model.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    List<UserDto> findAllUsers();

    UserDto updateUser(UserDto userDto);
}
