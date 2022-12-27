package com.guru.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.blogapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
