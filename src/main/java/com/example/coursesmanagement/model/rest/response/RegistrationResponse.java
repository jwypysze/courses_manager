package com.example.coursesmanagement.model.rest.response;

import com.example.coursesmanagement.model.dto.RegistrationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationResponse {

    private RegistrationDto registrationDto;

    @JsonIgnore
    public static RegistrationResponse from(RegistrationDto source) {
        return RegistrationResponse.builder()
                .registrationDto(source)
                .build();
    }
}
