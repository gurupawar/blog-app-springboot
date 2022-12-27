package com.guru.blogapp.services;

import java.util.List;

import com.guru.blogapp.payloads.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto userUpdate(Long userId, UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    void deleteUser(Long userId);

}
