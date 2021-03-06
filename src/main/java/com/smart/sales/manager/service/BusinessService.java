package com.smart.sales.manager.service;

import java.util.List;
import java.util.Locale;

import com.smart.sales.manager.entity.model.Business;


public interface BusinessService {

    Business save(Business business);
   // Business saveBusiness(Business business);
    List<Business> findAll(int page, String order,String field);
    void delete(long id);
    void deleteByIdAndOwnerId(long id,String owner_name,boolean isDeleted);
    
    Business findByIdAndOwner(long id,long owner);

    Business findById(long id);
    
    Business update(Business business,String ownername, Locale locale);
    Business updateBusinessOnOff(long id, boolean status,String ownername, Locale locale);
    Business update(Business business);
    
	List<Business> findByName(String businessName); 
	List<Business> findByZipcode(String zipcode);
	
	List<Business> findByBusinessCategory(String category);
	List<Business> findByOwner(String ownerName);
	
	
}
