package com.smart.sales.manager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.sales.manager.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
