package com.example.coursesmanagement.model.dto;

import com.example.coursesmanagement.model.entity.BlockEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockDto {

    private Long id;
    private String blockTitle;

    @JsonIgnore
    public static BlockDto from(BlockEntity source) {
        return BlockDto.builder()
                .id(source.getId())
                .blockTitle(source.getBlockTitle())
                .build();
    }

}
