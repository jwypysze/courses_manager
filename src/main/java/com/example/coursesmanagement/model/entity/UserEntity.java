package com.example.coursesmanagement.model.entity;

import com.example.coursesmanagement.model.enums.ActiveUser;
import com.example.coursesmanagement.model.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private ActiveUser activeUser;

    @OneToMany(mappedBy = "userEntity")
    private List<RegistrationEntity> userRegistrations;

    @ManyToMany
    private List<NotificationEntity> notifications;

    public UserEntity
            (String login, String password, UserType userType, String name, String surname, ActiveUser activeUser) {
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.surname = surname;
        this.activeUser = activeUser;
    }

}
