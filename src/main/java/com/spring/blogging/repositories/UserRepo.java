package com.spring.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blogging.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User , Integer> {
    Optional<User> findByEmail(String username);
}
