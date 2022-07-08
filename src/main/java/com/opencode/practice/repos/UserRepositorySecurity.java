package com.opencode.practice.repos;

import com.opencode.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositorySecurity extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
