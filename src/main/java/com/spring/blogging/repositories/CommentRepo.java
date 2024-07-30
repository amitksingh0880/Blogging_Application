package com.spring.blogging.repositories;

import com.spring.blogging.entities.Comment;
import com.spring.blogging.payloads.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
