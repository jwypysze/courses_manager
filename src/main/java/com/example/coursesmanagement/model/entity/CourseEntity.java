package com.example.coursesmanagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "courseEntity")
    private List<RegistrationEntity> userRegistrations;

    @OneToMany(mappedBy = "courseEntity")
    private List<BlockEntity> blocks;

    @Column(name = "image_name")
    private String imageName;

    public CourseEntity(String title) {
        this.title = title;
    }

    public CourseEntity(String title, String imageName) {
        this.title = title;
        this.imageName = imageName;
    }

}
