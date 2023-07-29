package com.example.coursesmanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private  List<UserEntity> users = new ArrayList<>();

    public NotificationEntity(String topic, String text) {
        this.topic = topic;
        this.text = text;
    }

}
