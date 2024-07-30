package com.spring.blogging.services.implementation;

import com.spring.blogging.entities.Comment;
import com.spring.blogging.entities.Post;
import com.spring.blogging.exceptions.ResourceNotFoundException;
import com.spring.blogging.payloads.CommentDto;
import com.spring.blogging.repositories.CommentRepo;
import com.spring.blogging.repositories.PostRepo;
import com.spring.blogging.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post id", postId));
         Comment comment = this.modelMapper.map(commentDto, Comment.class);
         comment.setPost(post);
       Comment savedComment =  this.commentRepo.save(comment);
         return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

          Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","comment id",commentId));
             this.commentRepo.delete(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {

        Comment prevComment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment id", commentId ));
                prevComment.setContent(commentDto.getContent());

              Comment newComment =  this.commentRepo.save(prevComment);
        return this.modelMapper.map(newComment, CommentDto.class);
    }
}
