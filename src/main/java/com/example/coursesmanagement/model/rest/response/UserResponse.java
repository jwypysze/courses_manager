package com.example.coursesmanagement.model.rest.response;

import com.example.coursesmanagement.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private UserDto userDto;

    @JsonIgnore
    public static UserResponse from(UserDto source) {
        return UserResponse.builder()
                .userDto(source)
                .build();
    }

}
