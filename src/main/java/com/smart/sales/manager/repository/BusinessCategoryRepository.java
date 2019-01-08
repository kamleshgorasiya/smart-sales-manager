package com.smart.sales.manager.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.smart.sales.manager.entity.model.BusinessCategory;


@Repository
public interface BusinessCategoryRepository extends PagingAndSortingRepository<BusinessCategory, Long> {
	
}

