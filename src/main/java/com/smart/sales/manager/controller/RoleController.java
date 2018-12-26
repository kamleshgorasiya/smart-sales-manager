package com.smart.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.smart.sales.manager.model.ApiResponse;
import com.smart.sales.manager.model.Role;
import com.smart.sales.manager.model.User;
import com.smart.sales.manager.model.UserDto;
import com.smart.sales.manager.service.RoleService;
import com.smart.sales.manager.service.UserService;

import java.util.List;


@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

  
    @PostMapping(value="/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Role> saveRole(@RequestBody Role role){
        return new ApiResponse<>(HttpStatus.OK.value(), "User role saved successfully.",roleService.save(role));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/roles")
    public ApiResponse<List<Role>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "User roles fetched successfully.",roleService.findAll());
    }
    
    @GetMapping("/role/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<User> getyProfile(@RequestBody String name){
        return new ApiResponse<>(HttpStatus.OK.value(), "User role fetched successfully.",roleService.findByName(name));
    }
    
    @GetMapping("/role/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<User> getOne(@PathVariable long id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User role fetched successfully.",roleService.findById(id));
    }

    @PutMapping("/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Role> update(@RequestBody Role role) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User role updated successfully.",roleService.update(role));
    }

    @DeleteMapping("/role/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Role> delete(@PathVariable long id) {
    	roleService.delete(id);
    	return new ApiResponse<>(HttpStatus.OK.value(), "User role deleted successfully.",null );
    }

}
