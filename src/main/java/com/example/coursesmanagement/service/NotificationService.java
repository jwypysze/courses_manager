package com.example.coursesmanagement.service;

import com.example.coursesmanagement.exception.exceptions.EntityNotFoundException;
import com.example.coursesmanagement.model.dto.NotificationDto;
import com.example.coursesmanagement.model.entity.BlockEntity;
import com.example.coursesmanagement.model.entity.ClassEntity;
import com.example.coursesmanagement.model.entity.NotificationEntity;
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

    public List<Long> findNotificationsByClass(ClassEntity classEntity) {
        return notificationJpaRepository.findNotificationsByClass(classEntity);
    }

    public void deleteNotificationById(NotificationDto notificationDto) {
        NotificationEntity notificationEntity =
                notificationJpaRepository.findById(notificationDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException
                                (NotificationEntity.class, notificationDto.getId()));
        notificationJpaRepository.delete(notificationEntity);
    }

    public void deleteNotificationsFromTableUsersNotifications(NotificationDto notificationDto) {
        NotificationEntity notificationEntity = notificationJpaRepository.findById(notificationDto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException
                                (NotificationEntity.class, notificationDto.getId()));
        notificationJpaRepository.deleteNotificationsFromTableUsersNotifications(notificationEntity.getId());
    }
}
