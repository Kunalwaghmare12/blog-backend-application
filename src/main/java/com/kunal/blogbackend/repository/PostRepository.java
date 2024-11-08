package com.kunal.blogbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kunal.blogbackend.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

    List<Post> findByUserId(long userId);
    List<Post> findByCategoryId(long categoryId);
    List<Post> findByTitleContainingOrContentContaining(String title, String content);

}
