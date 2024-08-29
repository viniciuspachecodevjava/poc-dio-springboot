package com.poc.diospringboot.gateway.database.repository;

import com.poc.diospringboot.gateway.database.model.UserDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDatabase, Long> {
    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

//  @Query("SELECT u FROM user_database u WHERE u.email = :email")
    Optional<UserDatabase> findByEmail(String email);
}
