package com.kunal.blogbackend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kunal.blogbackend.dto.PostDto;
import com.kunal.blogbackend.entity.Category;
import com.kunal.blogbackend.entity.Post;
import com.kunal.blogbackend.entity.User;
import com.kunal.blogbackend.exception.ResourceNotFoundException;
import com.kunal.blogbackend.repository.CategoryRepository;
import com.kunal.blogbackend.repository.PostRepository;
import com.kunal.blogbackend.repository.UserRepository;
import com.kunal.blogbackend.service.PostService;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper,UserRepository userRepository,CategoryRepository categoryRepository){
        this.postRepository=postRepository;
        this.modelMapper=modelMapper;
        this.userRepository=userRepository;
        this.categoryRepository=categoryRepository;
    }

    // create-post
    @Override
    public PostDto createPost(PostDto postDto) {
        //fetching user first
        User user =userRepository.findById(postDto.getUserId()).orElseThrow(()->new ResourceNotFoundException("User", "Id", postDto.getUserId()));
        //fetching category first
        Category category= categoryRepository.findById(postDto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category","Id",postDto.getCategoryId()));
        Post post=modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost=postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    // update-post
    @Override
    public PostDto updatePost(PostDto postDto){
        User user=userRepository.findById(postDto.getUserId()).orElseThrow(()->new ResourceNotFoundException("User", "Id",postDto.getUserId()));
        Category category=categoryRepository.findById(postDto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Category", "Id",postDto.getCategoryId()));
        Post post=postRepository.findById(postDto.getId()).orElseThrow(()->new ResourceNotFoundException("Post", "Id",postDto.getId()));
        post.setCategory(category);
        post.setUser(user);
        Post foundpost=modelMapper.map(postDto,Post.class);
        return modelMapper.map(foundpost,PostDto.class);
    }

    @Override
    public PostDto getPostById(long pId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostById'");
    }

    @Override
    public PostDto getPostByCategoryId(long cId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostByCategoryId'");
    }

    @Override
    public List<PostDto> getAllPostByUserId(long uId) {
        User user=userRepository.findById(uId).orElseThrow(()->new ResourceNotFoundException("User", "Id",uId));
        List<Post> posts=postRepository.findByUserId(uId);
        List<PostDto> postDtos=posts.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;

    }

    @Override
    public List<PostDto> getAllPosts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPosts'");
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        // this is the approach - 1
        // first fetch all post from database into list then extract post from list based on keyword
        List<Post> allPosts= postRepository.findAll();
        List<Post> searchedPost = allPosts.stream()
        .filter(post -> post.getTitle().toLowerCase().contains(keyword.toLowerCase()) 
                     || post.getContent().toLowerCase().contains(keyword.toLowerCase()))
        .collect(Collectors.toList());
            List<PostDto> searchPostDtos=searchedPost.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return searchPostDtos;

        // approach - 2
        // directly fetch data from database and show to user
        // List<Post> posts = postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        // List<PostDto> postDtos=posts.stream().map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        // return postDtos;

    }

    @Override
    public void deletePost(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    

}
