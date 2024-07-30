package com.spring.blogging.controllers;

import com.spring.blogging.entities.Comment;
import com.spring.blogging.payloads.ApiResponse;
import com.spring.blogging.payloads.CommentDto;
import com.spring.blogging.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId)
    {
          CommentDto createdComment = this.commentService.createComment(comment,postId);
          return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/post/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
    {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully",true),HttpStatus.OK);
    }

    @PutMapping("post/comments/{commentId}")
     public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable Integer commentId)
    {
          CommentDto updatedComment =  this.commentService.updateComment(commentDto,commentId);
       return new ResponseEntity<>(updatedComment , HttpStatus.OK);
    }

}
