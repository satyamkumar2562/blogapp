package com.blogapp2.service.impl;

import com.blogapp2.entity.Comment;
import com.blogapp2.entity.Post;
import com.blogapp2.payload.CommentDto;
import com.blogapp2.payload.PostDto;
import com.blogapp2.payload.PostWithCommentDto;
import com.blogapp2.repository.CommentRepository;
import com.blogapp2.repository.PostRepository;
import com.blogapp2.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).get();
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);
        CommentDto dto = mapToDto(save);
        return dto;

    }
   /*public List<CommentDto>getAllCommentsByPostId(long id){
        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDto> collect = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return collect;
    }*/

    public PostWithCommentDto getAllCommentsByPostId(long id){
        Post post = postRepository.findById(id).get();
        PostDto dto=mapToDto(post);

       /* PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
*/

        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDto> collect = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());

        PostWithCommentDto dto1=new PostWithCommentDto();
        dto1.setCommentdto(collect);
        dto1.setPost(dto);
        return  dto1;

    }
    Comment mapToEntity(CommentDto commentDto){
    Comment comment=modelMapper.map(commentDto,Comment.class);
        return comment;
    }
    CommentDto mapToDto(Comment comment){
        CommentDto dto= modelMapper.map(comment,CommentDto.class);
        return dto;
    }
    PostDto mapToDto(Post post){
        PostDto dto= modelMapper.map(post,PostDto.class);
        return dto;
    }
}
