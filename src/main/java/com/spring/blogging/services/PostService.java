package com.spring.blogging.services;

import com.spring.blogging.entities.Post;
import com.spring.blogging.payloads.PostDto;
import com.spring.blogging.payloads.PostResponse;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {

    //CREATE
    PostDto createPost(PostDto postDto , Integer userId, Integer categoryId );
    //UPDATE
    PostDto updatePost(PostDto postDto , Integer postId);

    //DELETE
    void deletePost(Integer postId);

    //GET
    PostDto getPostById(Integer postId);

    //GET BY CATEGORY
    List<PostDto> getPostByCategory(Integer categoryId);

    //GET ALL POST
//    List<PostDto> getAllPost(Integer pageNumber , Integer pageSize);
    PostResponse getAllPost(Integer pageNumber , Integer pageSize , String sortBy, String order);

    //GET ALL POSTS BY USER
    List<PostDto> getPostByUser(Integer userId);

    //POST SEARCH
    List<PostDto> searchPost(String keyword);

}
