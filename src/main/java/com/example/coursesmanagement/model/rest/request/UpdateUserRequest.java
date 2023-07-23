package com.example.coursesmanagement.model.rest.request;

import com.example.coursesmanagement.model.dto.UserDto;
import com.example.coursesmanagement.model.enums.ActiveUser;
import com.example.coursesmanagement.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {

    private Long id;
    private String login;
    private String password;
    private UserType userType;
    private String name;
    private String surname;
    private ActiveUser activeUser;

    @JsonIgnore
    public static UserDto toDto(UpdateUserRequest source) {
        return UserDto.builder()
                .id(source.getId())
                .login(source.getLogin())
                .password(source.getPassword())
                .userType(source.getUserType())
                .name(source.getName())
                .surname(source.getSurname())
                .activeUser(source.getActiveUser())
                .build();
    }
}
