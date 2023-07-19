package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.entity.CourseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long id;
    private String title;

    @JsonIgnore
    public static CourseDto from(CourseEntity source) {
        return CourseDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .build();
    }

}
