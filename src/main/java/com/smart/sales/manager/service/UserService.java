package com.smart.sales.manager.service;

import java.util.List;

import com.smart.sales.manager.model.User;
import com.smart.sales.manager.model.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);
    User findById(long id);
    User update(UserDto user);
    User updateMe(UserDto user,String username);
}
