package com.example.coursesmanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToOne(mappedBy = "courseEntity")
    private UserRegistrationEntity userRegistrationEntity;

    @OneToMany(mappedBy = "courseEntity")
    private List<BlockEntity> blockEntities;
}
