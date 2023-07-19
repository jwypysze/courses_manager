package com.example.coursesmanagement.model.entity;

import com.example.coursesmanagement.model.enums.ActiveUser;
import com.example.coursesmanagement.model.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "is_active")
    @Enumerated(EnumType.STRING)
    private ActiveUser ActiveUser;

    @OneToMany(mappedBy = "userEntity")
    private List<UserRegistrationEntity> userRegistrations;

    @ManyToMany
    private List<NotificationEntity> notifications;

}
