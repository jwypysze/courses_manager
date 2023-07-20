package com.example.coursesmanagement.model.rest.response;

import com.example.coursesmanagement.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindAllUsersResponse {

    private List<UserDto> userDto;

    @JsonIgnore
    public static FindAllUsersResponse from(List<UserDto> source) {
        return FindAllUsersResponse.builder()
                .userDto(source)
                .build();
    }
}
