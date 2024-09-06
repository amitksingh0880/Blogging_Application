package com.spring.blogging.repositories;

import com.spring.blogging.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepo extends JpaRepository<Roles, Integer> {

}