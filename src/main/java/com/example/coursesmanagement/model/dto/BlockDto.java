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
    private String courseTitle;
    private Long courseId;

    public BlockDto(String blockTitle) {
        this.blockTitle = blockTitle;
    }

    public BlockDto(Long id, String blockTitle, String courseTitle) {
        this.id = id;
        this.blockTitle = blockTitle;
        this.courseTitle = courseTitle;
    }

    public BlockDto(Long id, String blockTitle, CourseEntity courseEntity) {
        this.id = id;
        this.blockTitle = blockTitle;
        this.courseEntity = courseEntity;
    }
}
