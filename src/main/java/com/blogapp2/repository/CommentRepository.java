package com.blogapp2.repository;

import com.blogapp2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
  List<Comment> findByPostId(long postId);

}
