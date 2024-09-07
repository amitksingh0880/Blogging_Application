package com.spring.blogging.controllers;

import com.spring.blogging.config.AppConstants;
import com.spring.blogging.entities.Post;
import com.spring.blogging.exceptions.PostNotFoundException;
import com.spring.blogging.exceptions.ResourceNotFoundException;
import com.spring.blogging.payloads.ApiResponse;
import com.spring.blogging.payloads.PostDto;
import com.spring.blogging.payloads.PostResponse;
import com.spring.blogging.services.FileService;
import com.spring.blogging.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

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
     public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize", defaultValue =AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                     @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                      @RequestParam(value = "Order",defaultValue = AppConstants.ORDER , required = false) String order)
      {
          PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy ,order);
          if (postResponse == null) {
              throw new PostNotFoundException("No posts found");
          }
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

     //SEARCH
    @GetMapping("/posts/search/{keywords}")
     public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
    {
        List<PostDto> result = this.postService.searchPost(keywords);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }


    //POST IMAGE UPLOAD
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image, @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
         String fileName = this.fileService.uploadImage(path , image);
        postDto.setImageName(fileName);
      PostDto updatedPost =  this.postService.updatePost(postDto , postId);
      return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    @GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
    {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }


}
