package com.spring.blogging.repositories;

import com.spring.blogging.entities.Category;
import com.spring.blogging.entities.Post;
import com.spring.blogging.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

         List<Post> findByUser(User user);
         List<Post> findByCategory(Category category);
}
