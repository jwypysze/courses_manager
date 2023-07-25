package com.example.coursesmanagement.service;

import com.example.coursesmanagement.model.dto.CourseDto;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.repository.CourseJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseJpaRepository courseJpaRepository;

    public void addCourse(CourseDto courseDto, MultipartFile file) {
        CourseEntity courseEntity = new CourseEntity(courseDto.getTitle(), courseDto.getImageName());
        courseJpaRepository.save(courseEntity);
        saveCourseImage(file);
    }

    private void saveCourseImage(MultipartFile file) {
        Path uploads = Paths.get("./uploads");
        try {
            Files.copy(file.getInputStream(), uploads.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public List<CourseDto> getCourses() {
        return courseJpaRepository.findAll().stream()
                .map(courseEntity ->
                        new CourseDto(courseEntity.getId(), courseEntity.getTitle(),
                                courseEntity.getImageName())).toList();
    }


}
