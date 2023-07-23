package com.example.coursesmanagement.model.rest.response;

import com.example.coursesmanagement.model.dto.CourseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {

    private CourseDto courseDto;

    @JsonIgnore
    public static CourseResponse from(CourseDto source) {
        return CourseResponse.builder()
                .courseDto(source)
                .build();
    }
}
