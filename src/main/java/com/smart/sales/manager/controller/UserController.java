package com.smart.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.smart.sales.manager.model.ApiResponse;
import com.smart.sales.manager.model.User;
import com.smart.sales.manager.model.UserDto;
import com.smart.sales.manager.service.UserService;

import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;
  
    @PostMapping(value="/signup")
    public ApiResponse<User> saveUser(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody UserDto user){
    
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.saved", null, locale),userService.save(user));
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/users")
    public ApiResponse<List<User>> listUser(@RequestHeader(name="Accept-language",required=false) Locale locale){
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.list.fetched", null, locale),userService.findAll());
    }
    
    @GetMapping("/users/me")
    public ApiResponse<User> getyProfile(@RequestHeader(name="Accept-language",required=false) Locale locale, Authentication authentication){
    	String username= authentication.getName();
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.fetched", null, locale),userService.findOne(username));
    }
    
    @PutMapping("/users/update/me")
    public ApiResponse<UserDto> updateMe(@RequestHeader(name="Accept-language",required=false) Locale locale,@RequestBody UserDto userDto,Authentication authentication) {
    	String username= authentication.getName();
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.profile.updated", null, locale),userService.updateMe(userDto,username));
    }
    
    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ApiResponse<User> getOne(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable long id){
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.fetched", null, locale),userService.findById(id));
    }

    @PutMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserDto> update(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody UserDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.updated", null, locale),userService.update(userDto));
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id) {
        
    	userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("user.deleted", null, locale), null);
    }

}
