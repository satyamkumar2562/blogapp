package com.blogapp2.controller;

import com.blogapp2.payload.ListPostDto;
import com.blogapp2.payload.PostDto;
import com.blogapp2.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")

public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<?>createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
         return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePostById(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("post deleted by id ",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ListPostDto> fetchAllPosts(

        @RequestParam(name="pageNumber",defaultValue = "0",required=false)  int pageNumber,
        @RequestParam(name="pageSize",defaultValue = "5",required =false) int pageSize,
        @RequestParam(name="sortTheString",defaultValue = "id",required =false) String sortTheString)
    {
    ListPostDto postDtoList =   postService.fetchAllPosts(pageNumber,pageSize,sortTheString);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable long id)
    {
        PostDto postById = postService.getPostById(id);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }
}
