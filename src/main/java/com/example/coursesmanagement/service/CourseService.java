package com.example.coursesmanagement.service;

import com.example.coursesmanagement.repository.CourseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseJpaRepository courseJpaRepository;


}
