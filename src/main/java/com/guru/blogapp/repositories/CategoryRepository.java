package com.guru.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.blogapp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
