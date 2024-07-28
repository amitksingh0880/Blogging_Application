package com.spring.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.blogging.entities.Category;

public interface CategoryRepo extends JpaRepository<Category , Integer> {

}
