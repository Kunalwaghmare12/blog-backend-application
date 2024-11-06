package com.kunal.blogbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kunal.blogbackend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByTitle(String title);
}
