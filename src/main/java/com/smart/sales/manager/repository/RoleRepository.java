package com.smart.sales.manager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.sales.manager.entity.model.Role;


@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String rolename);
	List<Role> findAllById(List<Long> ids);

}
