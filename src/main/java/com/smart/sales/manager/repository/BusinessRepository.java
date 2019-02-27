package com.smart.sales.manager.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smart.sales.manager.entity.model.Business;


@Repository
public interface BusinessRepository extends PagingAndSortingRepository<Business, Long> {
	
	//List<Business> findByName(String businessname);
	Page<Business> findByName(String businessname, Pageable pageable);
	 
	List<Business> findByZipcode(String zipcode,Pageable pageable);
	
	List<Business> findByBusinessCategory(String category, Pageable pageable);
	List<Business> findByOwner(String ownerId,Pageable pageable);
	 
	@Modifying
	@Query(value="update business u set u.is_deleted = :is_deleted where u.id = :id and u.owner = :owner", nativeQuery=true)
	@Transactional
	void updateIsDeleted(@Param("is_deleted")boolean is_deleted,@Param("id")long id,@Param("owner")String owner);
	
	Business findByIdAndOwner(long id, String owner);
	
	
	

}

