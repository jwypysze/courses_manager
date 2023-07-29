package com.example.coursesmanagement.repository;

import com.example.coursesmanagement.model.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {

    @Query
            (value = "DELETE FROM users_notifications WHERE users_id = :userId",
                    nativeQuery = true)
    @Modifying
    @Transactional
    void deleteUsersFromTableUsersNotifications(@Param("userId") Long userId);

}
