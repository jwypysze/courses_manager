package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.model.entity.RegistrationEntity;
import com.example.coursesmanagement.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private Long id;
    private LocalDateTime registrationDate;
    private UserEntity userEntity;
    private CourseEntity courseEntity;

    @JsonIgnore
    public static RegistrationDto from(RegistrationEntity source) {
        return RegistrationDto.builder()
                .id(source.getId())
                .registrationDate(source.getRegistrationDate())
                .userEntity(source.getUserEntity())
                .courseEntity(source.getCourseEntity())
                .build();
    }


}
