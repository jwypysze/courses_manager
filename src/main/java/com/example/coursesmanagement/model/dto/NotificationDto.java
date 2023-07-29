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
    private LocalDate date;
    private LocalTime time;

    public NotificationDto(Long id, String topic, String text,
                           String classTopic, LocalDate date, LocalTime time) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.classTopic = classTopic;
        this.date = date;
        this.time = time;
    }



}
