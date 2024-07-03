package com.blogapp2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Post {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String content;

    @OneToMany(mappedBy = "post",orphanRemoval = true,cascade = CascadeType.ALL)
   private List<Comment> comments= new ArrayList<>();



}
