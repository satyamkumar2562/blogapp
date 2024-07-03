package com.blogapp2.service.impl;

import com.blogapp2.entity.Post;
import com.blogapp2.exception.ResourceNotFound;
import com.blogapp2.payload.ListPostDto;
import com.blogapp2.payload.PostDto;
import com.blogapp2.repository.PostRepository;
import com.blogapp2.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedpost = postRepository.save(post);
        PostDto dto = mapToDto(savedpost);
        return dto;


    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    @Override
    public ListPostDto fetchAllPosts(int pageNumber, int pageSize, String sortTheString) {
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.by(sortTheString));
        Page<Post> all = postRepository.findAll(pages);
        List<Post> content = all.getContent();
        List<PostDto> collect = content.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        ListPostDto listPostDto=new ListPostDto();
        listPostDto.setPostDto(collect);
        listPostDto.setTotalpages(all.getTotalPages());
        listPostDto.setTotalelements((int) all.getTotalElements());
        listPostDto. setFirstpage(all.isFirst());
        listPostDto.setLastpage(all.isLast());
        listPostDto.setPagenumber(all.getNumber());
        return listPostDto;

    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFound("post not found with id:"+id));
        return mapToDto(post);
    }

  /*   @Override
    public List<PostDto> fetchAllPosts() {
        List<Post> post = postRepository.findAll();
        List<PostDto> postdto = post.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return postdto;
    } */

    Post mapToEntity(PostDto postDto){
    Post post=modelMapper.map(postDto,Post.class);
    return post;
   }
   PostDto mapToDto(Post post){
       PostDto dto= modelMapper.map(post,PostDto.class);
       return dto;
   }
}
