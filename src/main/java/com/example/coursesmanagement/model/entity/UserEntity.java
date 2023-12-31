package com.example.coursesmanagement.model.entity;

import com.example.coursesmanagement.model.enums.ActiveUser;
import com.example.coursesmanagement.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private UserRole userRole;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "is_active")
    @Enumerated(EnumType.STRING)
    private ActiveUser activeUser;

    @OneToMany(mappedBy = "userEntity")
    private List<RegistrationEntity> userRegistrations;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "users_notifications",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "notifications_id"))
    private List<NotificationEntity> notifications = new ArrayList<>();

    public UserEntity
            (String login, String password, UserRole userRole, String name, String surname, ActiveUser activeUser) {
        this.login = login;
        this.password = password;
        this.userRole = userRole;
        this.name = name;
        this.surname = surname;
        this.activeUser = activeUser;
    }

}
