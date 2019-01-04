package com.smart.sales.manager.service;

import java.util.List;

import com.smart.sales.manager.entity.model.Role;



public interface RoleService {

	Role save(Role role);
    List<Role> findAll();
    void delete(long id);
    Role findByName(String rolename);
    Role findById(long id);
    Role update(Role role);
  
}
