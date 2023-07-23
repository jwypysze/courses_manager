package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.enums.ActiveUser;
import com.example.coursesmanagement.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private UserType userType;
    private String name;
    private String surname;
    private ActiveUser activeUser;


}
