package com.kunal.blogbackend.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kunal.blogbackend.dto.UserDto;
import com.kunal.blogbackend.service.UserService;
import com.kunal.blogbackend.utils.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    
    // Constructor injection
    public UserController(UserService userService){
        this.userService=userService;
    }

    // Create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody  UserDto userDto){
        UserDto savedUserDto=userService.createUser(userDto);
        return new ResponseEntity<>(savedUserDto,HttpStatus.CREATED);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id,@Valid @RequestBody  UserDto userDto){
        UserDto updatedUserDto=userService.updateUser(id, userDto);
        return new ResponseEntity<>(updatedUserDto,HttpStatus.CREATED);
    }

    // Getting user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id){
        UserDto foundUser=userService.getUserById(id);
        return new ResponseEntity<>(foundUser,HttpStatus.FOUND);
    }

    // Getting all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers=userService.getAllUser();
        return new ResponseEntity<>(allUsers,HttpStatus.FOUND);
    }

    // Delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("id") long id){
        userService.deleteUser(id);
        ApiResponse response=new ApiResponse();
        response.setMessage("user deleted sucessfully");
        response.setSuccess(true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    
}
