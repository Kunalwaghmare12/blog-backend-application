package com.kunal.blogbackend.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kunal.blogbackend.dto.UserDto;
import com.kunal.blogbackend.entity.User;
import com.kunal.blogbackend.exception.ResourceFoundException;
import com.kunal.blogbackend.exception.ResourceNotFoundException;
import com.kunal.blogbackend.repository.UserRepository;
import com.kunal.blogbackend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // Constructor injection
    UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    // creating new user
    @Override
    public UserDto createUser(UserDto userDto) {
        boolean isUserExist = userRepository.existsByEmail(userDto.getEmail());
        if (!isUserExist) {
            User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }else{

        throw new ResourceFoundException("User", "id", userDto.getId());
    }
        

    }

    // updating user by id
    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    // getting user by id
    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return modelMapper.map(user, UserDto.class);
    }

    // getting all users
    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

    }

    // Deleting user by id
    @Override
    public void deleteUser(long id) {
        User user= userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

}
