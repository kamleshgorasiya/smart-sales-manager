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
	
	@Modifying
	@Query(value="select * from business where owner_name = :owner_name and is_deleted!=1", nativeQuery=true)
	@Transactional
	List<Business> findByOwnerName(@Param("owner_name")String owner_name);
	 
	@Modifying
	@Query(value="update business u set u.is_deleted = :is_deleted, u.updated = :updated where u.id = :id and u.owner_name = :owner_name", nativeQuery=true)
	@Transactional
	void updateIsDeleted(@Param("is_deleted")boolean is_deleted,@Param("updated")long updated,@Param("id")long id,@Param("owner_name")String owner_name);
	
	
	@Modifying
	@Query(value="update business u set u.name = :#{#business.name},u.address = :#{#business.address},u.latitude = :#{#business.latitude},u.longitude = :#{#business.longitude}, u.city = :#{#business.city},u.state = :#{#business.state},u.country = :#{#business.country},u.zipcode = :#{#business.zipcode},u.category_id = :category_id,u.updated = :#{#business.updated} where u.id = :id and u.owner_id = :owner_id and  u.owner_name=:owner_name", nativeQuery=true)
	@Transactional
	void update(@Param("business")Business business,@Param("id")long id,@Param("owner_id")long owner_id,@Param("category_id")long category_id,@Param("owner_name")String owner_name);
	
	
	@Modifying
	@Query(value="update business u set u.updated = :updated,u.is_on=:status where u.id = :id and u.owner_name=:owner_name", nativeQuery=true)
	@Transactional
	void updateBusinessOnOff(@Param("id")long id,@Param("status")boolean status,@Param("owner_name")String owner_name,@Param("updated")long updated);
	
	
	Business findByIdAndOwnerId(long id, long ownerId);
	
	
	

}

