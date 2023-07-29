package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.BlockDto;
import com.example.coursesmanagement.model.dto.CourseDto;
import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.ClassEntity;
import com.example.coursesmanagement.model.entity.CourseEntity;
import com.example.coursesmanagement.model.entity.NotificationEntity;
import com.example.coursesmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseJpaRepository courseJpaRepository;
    private final BlockJpaRepository blockJpaRepository;
    private final ClassJpaRepository classJpaRepository;
    private final NotificationJpaRepository notificationJpaRepository;
    private final RegistrationJpaRepository registrationJpaRepository;

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

    public List<CourseDto> allCourses() {
        return courseJpaRepository.findAll().stream()
                .map(courseEntity ->
                        new CourseDto(courseEntity.getId(), courseEntity.getTitle(),
                                courseEntity.getImageName())).toList();
    }

    public CourseDto findCourseById(BlockDto blockDto) {
        CourseEntity courseEntity = courseJpaRepository.findById(blockDto.getCourseEntity().getId())
                .orElseThrow(() -> new EntityNotFoundException
                        (CourseEntity.class, blockDto.getCourseEntity().getId()));
        return new CourseDto(courseEntity.getId(), courseEntity.getTitle(), courseEntity.getImageName());
    }

    public CourseDto findCourseById(Long id) {
        CourseEntity courseEntity = courseJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        (CourseEntity.class, id));
        return new CourseDto(courseEntity.getId(), courseEntity.getTitle(), courseEntity.getImageName());
    }

    public void deleteCourseById(CourseDto courseDto) {
        CourseEntity courseEntity =
                courseJpaRepository.findById(courseDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException(
                                CourseEntity.class, courseDto.getId()));
        List<Long> blocksIdByCourse = blockJpaRepository.findBlocksByCourse(courseEntity);
        for(Long blockId : blocksIdByCourse) {
            BlockEntity blockEntityById = blockJpaRepository
                    .findById(blockId)
                    .orElseThrow(() -> new EntityNotFoundException(BlockEntity.class, blockId));
            List<Long> classesIdByBlock = classJpaRepository.findClassesByBlock(blockEntityById);
            for(Long classId : classesIdByBlock) {
                ClassEntity classEntityById = classJpaRepository.findById(classId)
                        .orElseThrow(() -> new EntityNotFoundException(ClassEntity.class, classId));
                List<Long> notificationsIdByClass =
                        notificationJpaRepository.findNotificationsByClass(classEntityById);
                for(Long notificationId : notificationsIdByClass) {
                    notificationJpaRepository.deleteNotificationsFromTableUsersNotifications(notificationId);
                }
                notificationsIdByClass.stream()
                        .forEach(notificationId -> notificationJpaRepository.deleteById(notificationId));
            }
            classesIdByBlock.stream()
                    .forEach(classId -> classJpaRepository.deleteById(classId));
        }
        blocksIdByCourse.stream()
                .forEach(blockId -> blockJpaRepository.deleteById(blockId));

        List<Long> registrationsIdByCourse =
                registrationJpaRepository.findRegistrationsByCourse(courseEntity);
        registrationsIdByCourse.stream()
                        .forEach(registrationId -> registrationJpaRepository.deleteById(registrationId));
        courseJpaRepository.delete(courseEntity);
    }


}
