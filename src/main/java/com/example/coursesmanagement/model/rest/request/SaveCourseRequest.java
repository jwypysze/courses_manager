package com.example.coursesmanagement.model.rest.request;

import com.example.coursesmanagement.model.dto.CourseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveCourseRequest {

    private String title;

    @JsonIgnore
    public static CourseDto toDto(SaveCourseRequest source) {
        return CourseDto.builder()
                .title(source.getTitle())
                .build();
    }
}
