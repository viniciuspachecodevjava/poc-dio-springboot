package com.poc.diospringboot.gateway.database.repository;

import com.poc.diospringboot.gateway.database.model.UserDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDatabase, Long> {
    boolean existsByEmail(String email);
}
