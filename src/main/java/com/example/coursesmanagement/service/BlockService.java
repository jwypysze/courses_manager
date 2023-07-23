package com.example.coursesmanagement.service;

import com.example.coursesmanagement.repository.BlockJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockService {

    private final BlockJpaRepository blockJpaRepository;



}
