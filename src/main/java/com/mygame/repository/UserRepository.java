package com.mygame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mygame.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserName(String username);
}
