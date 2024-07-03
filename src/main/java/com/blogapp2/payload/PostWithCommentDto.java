package com.blogapp2.payload;

import lombok.Data;

import java.util.List;
@Data
public class PostWithCommentDto {
    private PostDto post;
    private List<CommentDto> commentdto;
}
