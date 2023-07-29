package com.example.coursesmanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "classes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private BlockEntity blockEntity;

    @OneToMany(mappedBy = "classEntity")
    private List<NotificationEntity> notifications;

    public ClassEntity(String topic, LocalDate date, LocalTime time) {
        this.topic = topic;
        this.date = date;
        this.time = time;
    }

    public ClassEntity (String topic, LocalDate date, LocalTime time, BlockEntity blockEntity) {
        this.topic = topic;
        this.date = date;
        this.time = time;
        this.blockEntity = blockEntity;
    }
}
