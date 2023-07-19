package com.example.coursesmanagement.service;

import com.example.coursesmanagement.repository.BlockJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class BlockService {

    private final BlockJpaRepository blockJpaRepository;

    public BlockService(BlockJpaRepository blockJpaRepository) {
        this.blockJpaRepository = blockJpaRepository;
    }



}
