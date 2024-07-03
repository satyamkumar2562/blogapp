package com.blogapp2.controller;

import com.blogapp2.payload.CommentDto;
import com.blogapp2.payload.PostWithCommentDto;
import com.blogapp2.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @PathVariable long postId){
        CommentDto dto = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity <PostWithCommentDto> getAllCommentsByPostId(@PathVariable long postId){
        PostWithCommentDto allCommentsByPostId = commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(allCommentsByPostId,HttpStatus.OK);
    }
}
