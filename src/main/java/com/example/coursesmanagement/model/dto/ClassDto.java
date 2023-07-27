package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.entity.BlockEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {

    private Long id;
    private String topic;
    private LocalDateTime date;
    private Long blockId;
    private BlockEntity blockEntity;

    public ClassDto(String topic, LocalDateTime date, Long blockId) {
        this.topic = topic;
        this.date = date;
        this.blockId = blockId;
    }

}
