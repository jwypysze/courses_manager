package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlockDto {

    private Long id;
    private String blockTitle;
    private CourseEntity courseEntity;
    private Long courseId;

    public BlockDto(String blockTitle, Long courseId) {
        this.blockTitle = blockTitle;
        this.courseId = courseId;
    }

}
