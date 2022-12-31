package com.guru.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guru.blogapp.payloads.ApiResponse;
import com.guru.blogapp.payloads.PostDto;
import com.guru.blogapp.payloads.PostResponse;
import com.guru.blogapp.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    // Create
    // http://localhost:8080/api/user/4/category/1/posts
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Long userId,
            @PathVariable Long categoryId) {

        PostDto createdPost = this.postService.CreatePost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    // Get posts by category id
    // http://localhost:8080/api/category/4/posts
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Long categoryId) {
        List<PostDto> postList = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postList, HttpStatus.OK);
    }

    // Get posts by user
    // http://localhost:8080/api/user/4/posts
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Long userId) {
        List<PostDto> postList = postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(postList, HttpStatus.OK);
    }

    // Update Post by id
    @PutMapping("/user/{userId}/post")
    public ResponseEntity<?> updatePost() {
        return null;
    }

    // Delete Post by id
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }

    // Get all post
    // http://localhost:8080/api/posts - will get default pageNumber and PageSize
    // http://localhost:8080/api/posts?pageNumber=0&pageSize=3 - pageNumber start
    // with 0
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0") Long pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") Long pageSize) {
        PostResponse postResponse = postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    // Get Post by id
    // http://localhost:8080/api/post/
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }

    // Update post by id
    @PutMapping("/post/{postId}")
    // http://localhost:8080/api/post/5
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable Long postId) {
        PostDto post = postService.UpdatePost(postDto, postId);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }
}
