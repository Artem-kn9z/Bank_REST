package com.example.bankcards.repository.user;

import com.example.bankcards.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByFullName(String fullName);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
