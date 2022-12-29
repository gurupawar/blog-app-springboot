package com.guru.blogapp.services;

import java.util.List;

import com.guru.blogapp.entities.Post;
import com.guru.blogapp.payloads.PostDto;

public interface PostService {

    PostDto CreatePost(PostDto postDto, Long userId, Long categoryId);

    Post UpdatePost(PostDto postDto, Long postId);

    void deletePost(Long postId);

    List<Post> getAllPost();

    Post getPostById(Long postId);

    List<PostDto> getPostsByCategory(Long categoryId);

    List<PostDto> getPostsByUser(Long userId);

}
