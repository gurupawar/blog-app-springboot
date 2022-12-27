package com.guru.blogapp.controllers;

import java.net.http.HttpTimeoutException;
import java.util.List;

import org.hibernate.usertype.UserVersionType;
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
import org.springframework.web.bind.annotation.RestController;

import com.guru.blogapp.payloads.UserDto;
import com.guru.blogapp.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST - create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    // PUT - update user
    @PutMapping("/{userId}")
    public void updateUser(@RequestBody UserDto userDto, @PathVariable Long userId) {
        userService.userUpdate(userId, userDto);
    }

    // DELETE - delete user
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    // GET - user get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // GET - get all users
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        List<UserDto> alluser = userService.getAllUsers();
        return alluser;
    }
}
