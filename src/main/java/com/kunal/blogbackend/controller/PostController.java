package com.kunal.blogbackend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunal.blogbackend.dto.PostDto;
import com.kunal.blogbackend.service.PostService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    @PostMapping("/")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto savedPost=postService.createPost(postDto);
        return new ResponseEntity<>(savedPost,HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<PostDto> updatePost(@PathVariable ("id") long id,@RequestBody PostDto postDto){
        PostDto updatedPost=postService.updatePost(postDto);
        return new ResponseEntity<>(updatedPost,HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable ("userId") long userId){
        List<PostDto> postsByUser=postService.getAllPostByUserId(userId);
        return new ResponseEntity<>(postsByUser,HttpStatus.FOUND);

    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword) {
        List<PostDto> foundPost=postService.searchPost(keyword);
        return new ResponseEntity<>(foundPost,HttpStatus.FOUND);

    }
    

}
