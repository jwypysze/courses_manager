package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.NotificationDto;
import com.example.coursesmanagement.model.entity.ClassEntity;
import com.example.coursesmanagement.model.entity.NotificationEntity;
import com.example.coursesmanagement.repository.ClassJpaRepository;
import com.example.coursesmanagement.repository.NotificationJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationJpaRepository notificationJpaRepository;
    private final ClassJpaRepository classJpaRepository;

    public List<Long> findNotificationsByClass(ClassEntity classEntity) {
        return notificationJpaRepository.findNotificationsByClass(classEntity);
    }

    public void deleteNotificationById(NotificationDto notificationDto) {
        NotificationEntity notificationEntity =
                notificationJpaRepository.findById(notificationDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException
                                (NotificationEntity.class, notificationDto.getId()));
        notificationJpaRepository
                .deleteNotificationsFromTableUsersNotifications(notificationEntity.getId());
        notificationJpaRepository.delete(notificationEntity);
    }


    public List<NotificationDto> getAllNotifications() {
        return notificationJpaRepository.findAll().stream()
                .map(notificationEntity ->
                        new NotificationDto(notificationEntity.getId(), notificationEntity.getTopic(),
                                notificationEntity.getText(),
                                notificationEntity.getClassEntity().getTopic(),
                                notificationEntity.getClassEntity().getDate(),
                                notificationEntity.getClassEntity().getTime(),
                                notificationEntity.getClassEntity().getBlockEntity().getBlockTitle(),
                                notificationEntity.getClassEntity().getBlockEntity().getCourseEntity().getTitle()))
                .toList();
    }

    public void addNotification(NotificationDto notificationDto) {
        ClassEntity classEntity = classJpaRepository
                .findById(notificationDto.getClassId()).orElseThrow(() ->
                        new EntityNotFoundException(ClassEntity.class,
                                notificationDto.getClassId()));
        NotificationEntity notificationEntity =
                new NotificationEntity(notificationDto.getTopic(),
                        notificationDto.getText(), classEntity);
        notificationJpaRepository.save(notificationEntity);
    }

    public void updateNotificationById(NotificationDto notificationDto) {
        NotificationEntity notificationEntity = notificationJpaRepository
                .findById(notificationDto.getId())
                .orElseThrow(() ->
                new EntityNotFoundException(NotificationEntity.class, notificationDto.getId()));
        notificationJpaRepository.updateNotificationsById
                (notificationDto.getTopic(), notificationDto.getText(),
                        notificationDto.getClassId(), notificationEntity.getId());
    }
}
