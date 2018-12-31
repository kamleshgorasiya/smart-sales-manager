package com.smart.sales.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.sales.manager.model.Role;
import com.smart.sales.manager.model.User;
import com.smart.sales.manager.model.UserDto;
import com.smart.sales.manager.repository.RoleRepository;
import com.smart.sales.manager.repository.UserRepository;
import com.smart.sales.manager.service.UserService;

import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	 private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(long id) {
		return userRepository.findById(id).get();
	}

	@Override
    public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setFirstName(user.getFirstName());
	    newUser.setLastName(user.getLastName());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		 List<Role> userRoleList = new  ArrayList<Role>();
		Role userRole=roleRepository.findByName("ADMIN");
		userRoleList.add(userRole);
		newUser.setRoles(userRoleList);
		newUser.setSalary(user.getSalary());
		newUser.setEmail(user.getEmail());
		newUser.setMobile(user.getMobile());
        return userRepository.save(newUser);
    }

    @Override
    public User update(UserDto userDto) {
        User user = findById(userDto.getId());
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password","username","id");
            List<Role> userRoleList = new  ArrayList<Role>();
            if(userDto.getRole_ids()==null||userDto.getRole_ids().size()==0)
            {
            	Role userRole=roleRepository.findByName("USER");
            	userRoleList.add(userRole);
            }
            else    		
            userRoleList.addAll(roleRepository.findAllById(userDto.getRole_ids()));
            user.setRoles(userRoleList);
            return userRepository.save(user);
        }
        return user;
    }
    
    @Override
    public User updateMe(UserDto userDto,String username) {
        User user = findOne(username);
        if(user != null) {
            BeanUtils.copyProperties(userDto, user, "password","username","id");
            return userRepository.save(user);
        }
        return user;
    }

	
}
