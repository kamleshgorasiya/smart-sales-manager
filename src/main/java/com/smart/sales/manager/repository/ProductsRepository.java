package com.smart.sales.manager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.sales.manager.entity.model.Product;




@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

	List<Product> findByBusinessId(long businessId);
	
	
}

