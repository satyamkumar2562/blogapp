package com.blogapp2.service;

import com.blogapp2.payload.CommentDto;
import com.blogapp2.payload.PostWithCommentDto;

import java.util.List;

public interface CommentService {
  CommentDto createComment (CommentDto commentDto, long postId);
  PostWithCommentDto getAllCommentsByPostId(long id);
}
