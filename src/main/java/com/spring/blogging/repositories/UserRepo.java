package com.spring.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blogging.entities.User;

public interface UserRepo extends JpaRepository<User , Integer> {

	   
}
