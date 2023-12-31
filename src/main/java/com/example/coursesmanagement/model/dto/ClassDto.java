package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.entity.BlockEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {

    private Long id;
    private String topic;
    private LocalDate date;
    private LocalTime time;
    private Long blockId;
    private BlockEntity blockEntity;
    private String blockTitle;
    private String courseTitle;

    public ClassDto(String topic, LocalDate date, LocalTime time, Long blockId) {
        this.topic = topic;
        this.date = date;
        this.time = time;
        this.blockId = blockId;
    }

    public ClassDto(Long id, String topic, LocalDate date, LocalTime time, String blockTitle, String courseTitle) {
        this.id = id;
        this.topic = topic;
        this.date = date;
        this.time = time;
        this.blockTitle = blockTitle;
        this.courseTitle = courseTitle;
    }

}
