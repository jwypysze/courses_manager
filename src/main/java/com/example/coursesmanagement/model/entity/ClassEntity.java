package com.example.coursesmanagement.model.entity;

import com.example.coursesmanagement.model.dto.ClassDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private BlockEntity blockEntity;

    @OneToMany(mappedBy = "classEntity")
    private List<NotificationEntity> notifications;

    public ClassEntity(String topic, LocalDateTime date) {
        this.topic = topic;
        this.date = date;
    }

}
