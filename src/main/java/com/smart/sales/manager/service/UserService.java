package com.smart.sales.manager.service;

import java.util.List;

import com.smart.sales.manager.entity.model.User;
import com.smart.sales.manager.request.model.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);
    User findById(long id);
    User update(UserDto user);
    User updateMe(UserDto user,String username);
    User saveAdmin(UserDto user);
    
    User findByEmail(String email);

    void updatePassword(String password, Long userId);
}
