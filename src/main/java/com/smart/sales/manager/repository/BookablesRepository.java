package com.smart.sales.manager.repository;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.smart.sales.manager.entity.model.Bookable;


@Repository
public interface BookablesRepository extends PagingAndSortingRepository<Bookable, Long> {

	List<Bookable> findByBusiness_Id(long businessId, PageRequest request);
	
	
}

