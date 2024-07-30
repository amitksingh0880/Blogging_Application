package com.spring.blogging.payloads;

import com.spring.blogging.entities.Category;
import com.spring.blogging.entities.Comment;
import com.spring.blogging.entities.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDTO category;
    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
