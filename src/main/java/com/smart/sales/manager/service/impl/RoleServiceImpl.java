package com.smart.sales.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.sales.manager.dao.RoleRepository;
import com.smart.sales.manager.dao.UserDao;
import com.smart.sales.manager.model.Role;
import com.smart.sales.manager.model.User;
import com.smart.sales.manager.model.UserDto;
import com.smart.sales.manager.service.RoleService;
import com.smart.sales.manager.service.UserService;

import java.util.*;


@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
	

	
	@Autowired
	 private RoleRepository roleRepository;

	
	public List<Role> findAll() {
		List<Role> list = new ArrayList<>();
		roleRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public Role findByName(String username) {
		return roleRepository.findByName(username);
	}

	@Override
	public Role findById(long id) {
		return roleRepository.findById(id).get();
	}

	@Override
    public Role save(Role role) {
		role.setId(0);
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        Role roleData = findById(role.getId());
        if(roleData != null) {
            BeanUtils.copyProperties(role, roleData, "id");           
            return roleRepository.save(role);
        }
        return roleData;
    }
    
    



	
}
