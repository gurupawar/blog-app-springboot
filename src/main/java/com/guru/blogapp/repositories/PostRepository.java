package com.guru.blogapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.blogapp.entities.Category;
import com.guru.blogapp.entities.Post;
import com.guru.blogapp.entities.User;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
