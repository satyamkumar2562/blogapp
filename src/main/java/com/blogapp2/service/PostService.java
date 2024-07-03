package com.blogapp2.service;

import com.blogapp2.payload.ListPostDto;
import com.blogapp2.payload.PostDto;

import java.util.List;

public interface PostService {

    public PostDto createPost (PostDto postDto);

    void deletePost(long id);

    ListPostDto fetchAllPosts(int pageNumber, int pageSize, String sortTheString);
    public PostDto getPostById(long id);
}

