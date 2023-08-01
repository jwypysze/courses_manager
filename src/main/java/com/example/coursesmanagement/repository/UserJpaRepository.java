package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.UserEntity;
import com.example.coursesmanagement.model.enums.ActiveUser;
import com.example.coursesmanagement.model.enums.UserType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {

    @Query
            (value = "DELETE FROM users_notifications WHERE users_id = :userId",
                    nativeQuery = true)
    @Modifying
    @Transactional
    void deleteUsersFromTableUsersNotifications(@Param("userId") Long userId);

    @Query
            (value = "UPDATE UserEntity u SET u.login = :newLogin, " +
                    "u.password = :newPassword, u.userType = :newUserType, " +
                    "u.name = :newName, u.surname = :newSurname, " +
                    "u.activeUser = :isActive WHERE u.id = :userId",
                    nativeQuery = false)
    @Modifying
    @Transactional
    void updateUserById(@Param("newLogin") String newLogin,
                         @Param("newPassword") String newPassword,
                         @Param("newUserType") UserType newUserType,
                         @Param("newName") String newName,
                         @Param("newSurname") String newSurname,
                         @Param("isActive") ActiveUser activeUser,
                         @Param("userId") Long userId);


    Optional<UserEntity> findUserByNameAndSurname(String name, String surname);
}
