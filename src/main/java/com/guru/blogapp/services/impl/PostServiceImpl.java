package com.guru.blogapp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.blogapp.entities.Category;
import com.guru.blogapp.entities.Post;
import com.guru.blogapp.entities.User;
import com.guru.blogapp.exceptions.ResourceNotFoundException;
import com.guru.blogapp.payloads.PostDto;
import com.guru.blogapp.repositories.CategoryRepository;
import com.guru.blogapp.repositories.PostRepository;
import com.guru.blogapp.repositories.UserRepository;
import com.guru.blogapp.services.PostService;

@Service
public class PostServiceImpl implements PostService {

        @Autowired
        private PostRepository postRepository;

        @Autowired
        private ModelMapper modelMapper;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Override
        public PostDto CreatePost(PostDto postDto, Long userId, Long categoryId) {

                User user = this.userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

                Category category = this.categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

                Post post = modelMapper.map(postDto, Post.class);
                post.setImageName("default.png");
                post.setPublishedDate(new Date());
                post.setUser(user);
                post.setCategory(category);

                this.postRepository.save(post);

                return this.modelMapper.map(post, PostDto.class);
        }

        @Override
        public PostDto UpdatePost(PostDto postDto, Long postId) {
                Post post = postRepository.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
                post.setTitle(postDto.getTitle());
                post.setContent(postDto.getContent());
                post.setImageName(postDto.getImageName());
                Post updatedPost = postRepository.save(post);

                return this.modelMapper.map(updatedPost, PostDto.class);
        }

        @Override
        public void deletePost(Long postId) {
                Post post = this.postRepository.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
                postRepository.delete(post);
        }

        @Override
        public List<PostDto> getAllPost() {
                List<Post> postss = postRepository.findAll();
                List<PostDto> pstDto = postss.stream().map(post -> this.modelMapper.map(post, PostDto.class))
                                .collect(Collectors.toList());
                return pstDto;
        }

        @Override
        public PostDto getPostById(Long postId) {
                Post post = postRepository.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

                return modelMapper.map(post, PostDto.class);
        }

        @Override
        public List<PostDto> getPostsByCategory(Long categoryId) {
                Category cat = categoryRepository.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id",
                                                categoryId));
                List<Post> posts = postRepository.findByCategory(cat);
                List<PostDto> postdto = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
                                .collect(Collectors.toList());
                return postdto;
        }

        @Override
        public List<PostDto> getPostsByUser(Long userId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
                List<Post> postList = postRepository.findByUser(user);
                List<PostDto> postdto = postList.stream().map(post -> this.modelMapper.map(post, PostDto.class))
                                .collect(Collectors.toList());
                return postdto;
        }

}
