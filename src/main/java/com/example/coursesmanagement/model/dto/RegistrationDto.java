package com.example.coursesmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Long userId;
    private Long courseId;
    private String userName;
    private String userSurname;
    private String courseTitle;

    public RegistrationDto(Long id, LocalDate date, LocalTime time, Long userId, Long courseId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.userId = userId;
        this.courseId = courseId;
    }

    public RegistrationDto(Long id, LocalDate date, LocalTime time, String userName,
                           String userSurname, String courseTitle) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.userName = userName;
        this.userSurname = userSurname;
        this.courseTitle = courseTitle;
    }
}
