package com.example.coursesmanagement.model.entity;

import com.example.coursesmanagement.model.dto.NotificationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    @Column(name = "topic")
    private String topic;

    @Column(name = "text")
    private String text;

    @ManyToMany(mappedBy = "notifications")
    private  List<UserEntity> users;

    public NotificationEntity(String topic, String text) {
        this.topic = topic;
        this.text = text;
    }

    @JsonIgnore
    public static NotificationEntity toNewEntity(NotificationDto source) {
        return NotificationEntity.builder()
                .topic(source.getTopic())
                .text(source.getText())
                .build();
    }

}
