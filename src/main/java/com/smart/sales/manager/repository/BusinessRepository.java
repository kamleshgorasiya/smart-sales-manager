package com.smart.sales.manager.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.smart.sales.manager.entity.model.Business;


@Repository
public interface BusinessRepository extends PagingAndSortingRepository<Business, Long> {
	
	//List<Business> findByName(String businessname);
	Page<Business> findByName(String businessname, Pageable pageable);
	 
	List<Business> findByZipcode(String zipcode,Pageable pageable);
	
	List<Business> findByBusinessCategory(String category, Pageable pageable);
	List<Business> findByOwner(String ownerId,Pageable pageable);
	void deleteByIdAndOwner(long id, String owner);
	

}

