package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.entity.ClassEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    private Long id;
    private String topic;
    private String text;
    private ClassEntity classEntity;
    private String classTopic;
    private LocalDate classDate;
    private LocalTime classTime;
    private String blockTitle;
    private String courseTitle;
    private Long classId;

    public NotificationDto(Long id, String topic, String text,
                           String classTopic, LocalDate classDate, LocalTime classTime,
                           String blockTitle, String courseTitle) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.classTopic = classTopic;
        this.classDate = classDate;
        this.classTime = classTime;
        this.blockTitle = blockTitle;
        this.courseTitle = courseTitle;
    }

    public NotificationDto(String topic, String text, Long classId) {
        this.topic = topic;
        this.text = text;
        this.classId = classId;
    }

}
