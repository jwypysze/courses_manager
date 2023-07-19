package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.entity.ClassEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {

    private Long id;
    private String topic;
    private LocalDateTime date;

    @JsonIgnore
    public static ClassDto from(ClassEntity source) {
        return ClassDto.builder()
                .id(source.getId())
                .topic(source.getTopic())
                .date(source.getDate())
                .build();
    }
}
