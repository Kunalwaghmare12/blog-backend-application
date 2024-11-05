package com.kunal.blogbackend.service;

import java.util.List;

import com.kunal.blogbackend.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(long id,UserDto userDto);
    UserDto getUserById(long id);
    List<UserDto> getAllUser();
    void deleteUser(long id);
}
