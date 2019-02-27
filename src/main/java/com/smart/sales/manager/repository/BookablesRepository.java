package com.smart.sales.manager.repository;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.smart.sales.manager.entity.model.Bookable;


@Repository
public interface BookablesRepository extends JpaRepository<Bookable, Long> {

	List<Bookable> findByBusinessId(long businessId);
	
	
}

