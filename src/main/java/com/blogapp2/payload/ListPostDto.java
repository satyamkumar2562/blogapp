package com.blogapp2.payload;

import lombok.Data;

import java.util.List;
@Data
public class ListPostDto {
    private List<PostDto> postDto;
   private int totalpages;
    private int totalelements;
    private boolean lastpage;
    private boolean firstpage;
    private int pagenumber;


}
