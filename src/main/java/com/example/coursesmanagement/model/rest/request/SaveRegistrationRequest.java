package com.example.coursesmanagement.model.rest.request;

import com.example.coursesmanagement.model.dto.RegistrationDto;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveRegistrationRequest {

    private UserEntity userEntity;
    private CourseEntity courseEntity;


    @JsonIgnore
    public static RegistrationDto toDto(SaveRegistrationRequest source) {
        return RegistrationDto.builder()
                .userEntity(source.getUserEntity())
                .courseEntity(source.getCourseEntity())
                .build();
    }

}
