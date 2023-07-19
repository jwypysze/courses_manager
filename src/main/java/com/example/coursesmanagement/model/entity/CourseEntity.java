package com.example.coursesmanagement.model.entity;

import com.example.coursesmanagement.model.dto.CourseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "courseEntity")
    private List<RegistrationEntity> userRegistrations;

    @OneToMany(mappedBy = "courseEntity")
    private List<BlockEntity> blocks;

    public CourseEntity(String title) {
        this.title = title;
    }

    @JsonIgnore
    public static CourseEntity toNewEntity(CourseDto source) {
        return CourseEntity.builder()
                .title(source.getTitle())
                .build();
    }
}
