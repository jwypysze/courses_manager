package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.entity.NotificationEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    private Long id;
    private String topic;
    private String text;

    @JsonIgnore
    public static NotificationDto from(NotificationEntity source) {
        return NotificationDto.builder()
                .id(source.getId())
                .topic(source.getTopic())
                .text(source.getText())
                .build();
    }
}
