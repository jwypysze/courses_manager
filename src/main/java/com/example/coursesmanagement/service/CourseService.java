package com.example.coursesmanagement.service;

import com.example.coursesmanagement.model.dto.CourseDto;

public interface CourseService {

    CourseDto saveCourse(CourseDto courseDto);
    CourseDto findCourseById(Long id);
}
