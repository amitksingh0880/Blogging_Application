package com.spring.blogging.controllers;

import com.spring.blogging.entities.Post;
import com.spring.blogging.payloads.ApiResponse;
import com.spring.blogging.payloads.PostDto;
import com.spring.blogging.payloads.PostResponse;
import com.spring.blogging.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    //CREATE
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId){
        PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    //GET--
    //A)-- By Category
      @GetMapping("/category/{categoryId}/posts")
      public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
      {
          List<PostDto> gotPosts = this.postService.getPostByCategory(categoryId);
          return new ResponseEntity<>(gotPosts,HttpStatus.OK);
      }
    //B)-- By User
      @GetMapping("/user/{userId}/posts")
      public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
      {
        List<PostDto> gotPosts = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(gotPosts,HttpStatus.OK);
      }

      @GetMapping("/posts")
     public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize", defaultValue ="10", required = false) Integer pageSize,
                                                     @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
                                                      @RequestParam(value = "Order",defaultValue = "ascending" , required = false) String order)
      {
          PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy ,order);
          return new ResponseEntity<>(postResponse , HttpStatus.OK);
      }

      //DELETE
     @DeleteMapping("posts/{postId}")
     public ApiResponse deletePost(@PathVariable Integer postId)
     {
         this.postService.deletePost(postId);
         return new  ApiResponse("Post deleted Successfully!", true);
     }

     @GetMapping("/posts/{postId}")
     public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
     {
         PostDto gotPost = this.postService.getPostById(postId);
         return new ResponseEntity<>(gotPost , HttpStatus.OK);
     }

     @PutMapping("/posts/{postId}")
     public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId)
     {
         PostDto updatedPost = this.postService.updatePost(postDto,postId);
         return new ResponseEntity<>(updatedPost , HttpStatus.OK);
     }

}
