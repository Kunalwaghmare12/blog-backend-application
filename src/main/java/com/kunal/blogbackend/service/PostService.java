package com.kunal.blogbackend.service;

import java.util.List;

import com.kunal.blogbackend.dto.PostDto;

public interface PostService {
    // create-post
    PostDto createPost(PostDto postDto);
    // update-post
    PostDto updatePost(PostDto postDto);
    // get post by Id
    PostDto getPostById(long pId);
    // get post by category
    PostDto getPostByCategoryId(long cId);
    // get post by specifice user
    List<PostDto> getAllPostByUserId(long uId);
    // get all post 
    List<PostDto> getAllPosts();
    // search post by using specific keyword
    List<PostDto> searchPost(String keyword);
    // delete post
    void deletePost(long id);

}
