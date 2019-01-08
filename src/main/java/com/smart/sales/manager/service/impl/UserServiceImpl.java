package com.smart.sales.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.smart.sales.manager.entity.model.Role;
import com.smart.sales.manager.entity.model.User;
import com.smart.sales.manager.repository.RoleRepository;
import com.smart.sales.manager.repository.UserRepository;
import com.smart.sales.manager.request.model.Constants;
import com.smart.sales.manager.request.model.UserDto;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	//	if(!user.isIs_active())
		//	throw new UsernameNotFoundException("Your account is still now activated.");
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.isIs_active(),true,true,!user.isDeleted(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
		//  authorities.add(new SimpleGrantedAuthority(role.getName()));
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		// return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll(int page, String order, String field) {
		Sort sort = null;
		if (order.equalsIgnoreCase("DESC")) {
			sort = new Sort(Direction.DESC, field);
		} else {
			sort = new Sort(Direction.ASC, field);
		}
		PageRequest request = PageRequest.of(page - 1, Constants.PAGESIZE, sort);
		List<User> list = new ArrayList<>();
		userRepository.findAll(request).iterator().forEachRemaining(list::add);
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
	public User saveAdmin(UserDto user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		List<Role> userRoleList = new ArrayList<Role>();
		Role userRole = roleRepository.findByName("ADMIN");
		userRoleList.add(userRole);
		newUser.setRoles(userRoleList);
		newUser.setSalary(user.getSalary());
		newUser.setEmail(user.getEmail());
		newUser.setMobile(user.getMobile());
		return userRepository.save(newUser);
	}

	@Override
	public User save(UserDto user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		List<Role> userRoleList = new ArrayList<Role>();
		Role userRole = roleRepository.findByName("USER");
		userRoleList.add(userRole);
		newUser.setRoles(userRoleList);
		newUser.setSalary(user.getSalary());
		newUser.setEmail(user.getEmail());
		newUser.setMobile(user.getMobile());
		return userRepository.save(newUser);
	}

	@Override
	public User saveUser(UserDto user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		List<Role> userRoleList = new ArrayList<Role>();
		Role userRole = roleRepository.findByName(user.getRole());
		userRoleList.add(userRole);
		newUser.setRoles(userRoleList);
		newUser.setSalary(user.getSalary());
		newUser.setEmail(user.getEmail());
		newUser.setMobile(user.getMobile());
		return userRepository.save(newUser);
	}

	@Override
	public User update(UserDto userDto) {
		System.out.println(userDto.toString());
		
		User user = findById(userDto.getId());
		System.out.println(user.toString());
		if (user != null) {
			BeanUtils.copyProperties(userDto, user, "password", "username", "id", "email", "mobile");
			List<Role> userRoleList = new ArrayList<Role>();
			Role userRole = roleRepository.findByName(userDto.getRole());
			userRoleList.add(userRole);				
			user.setRoles(userRoleList);
			return userRepository.save(user);
		}
		return user;
	}

	@Override
	public User updateMe(UserDto userDto, String username) {
		User user = findOne(username);
		if (user != null) {
			BeanUtils.copyProperties(userDto, user, "is_active", "password", "username", "id", "email", "mobile");
			return userRepository.save(user);
		}
		return user;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void updatePassword(String password, Long userId) {
		userRepository.updatePassword(password, userId);
	}

}
