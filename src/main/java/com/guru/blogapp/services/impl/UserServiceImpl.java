package com.guru.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guru.blogapp.entities.User;
import com.guru.blogapp.payloads.UserDto;
import com.guru.blogapp.repositories.UserRepository;
import com.guru.blogapp.services.UserService;
import com.guru.blogapp.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        System.out.println(savedUser);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto userUpdate(Long userId, UserDto userDto) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        User updatedUser = this.userRepository.save(user);
        UserDto userDto2 = this.userToDto(updatedUser);
        return userDto2;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect((Collectors.toList()));
        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(user);
        System.out.println("user deleted...");
    }

    private User dtoToUser(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        System.out.println("created");
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto uDto = new UserDto();

        uDto.setId(user.getId());
        uDto.setName(user.getName());
        uDto.setEmail(user.getEmail());
        uDto.setPassword(user.getPassword());
        uDto.setAbout(user.getAbout());
        return uDto;
    }

}
