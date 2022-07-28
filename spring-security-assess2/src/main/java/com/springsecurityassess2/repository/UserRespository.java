package com.springsecurityassess2.repository;

import com.springsecurityassess2.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRespository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.username= ?2")
    @Modifying
    @Transactional
    public void updateFailedAttempt(int failedAttempt, String username);
}
