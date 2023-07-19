package com.example.coursesmanagement.model.entity;

import com.example.coursesmanagement.model.dto.BlockDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "blocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "block_title")
    private String blockTitle;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;

    @OneToMany(mappedBy = "blockEntity")
    private List<ClassEntity> classes;

    public BlockEntity(String blockTitle) {
        this.blockTitle = blockTitle;
    }

    @JsonIgnore
    public static BlockEntity toNewEntity(BlockDto source) {
        return BlockEntity.builder()
                .blockTitle(source.getBlockTitle())
                .build();
    }

}
