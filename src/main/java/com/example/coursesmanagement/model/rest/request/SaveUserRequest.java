package com.example.coursesmanagement.model.rest.request;

import com.example.coursesmanagement.model.dto.UserDto;
import com.example.coursesmanagement.model.enums.ActiveUser;
import com.example.coursesmanagement.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {

    private String login;
    private String password;
    private UserType userType;
    private String name;
    private String surname;
    private ActiveUser activeUser;

    @JsonIgnore
    public static UserDto toDto(SaveUserRequest source) {
        return UserDto.builder()
                .login(source.getLogin())
                .password(source.getPassword())
                .userType(source.getUserType())
                .name((source.getName()))
                .surname(source.getSurname())
                .activeUser((source.getActiveUser()))
                .build();
    }



}
