package com.smart.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.smart.sales.manager.model.ApiResponse;
import com.smart.sales.manager.model.User;
import com.smart.sales.manager.model.UserDto;
import com.smart.sales.manager.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

  
    @PostMapping(value="/signup")
    public ApiResponse<User> saveUser(@RequestBody UserDto user){
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/users")
    public ApiResponse<List<User>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
    }
    
    @GetMapping("/users/me")
    public ApiResponse<User> getyProfile(Authentication authentication){
    	String username= authentication.getName();
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findOne(username));
    }
    
    @PutMapping("/users/update/me")
    public ApiResponse<UserDto> updateMe(@RequestBody UserDto userDto,Authentication authentication) {
    	String username= authentication.getName();
        return new ApiResponse<>(HttpStatus.OK.value(), "Your profile details updated successfully.",userService.updateMe(userDto,username));
    }
    
    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<User> getOne(@PathVariable long id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.findById(id));
    }

    @PutMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }

}
