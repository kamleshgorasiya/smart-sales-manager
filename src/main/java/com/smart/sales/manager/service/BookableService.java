package com.smart.sales.manager.service;

import java.util.List;
import java.util.Locale;

import com.smart.sales.manager.entity.model.Bookable;


public interface BookableService {

    Bookable save(Bookable service,String username);   
    List<Bookable> findAll(int page, String order,String field);
    void delete(long id);    
    Bookable findById(long id);
    
    List<Bookable> findByBusinessId(long businessId, String username);
	Bookable update(Bookable bookable, String username, Locale locale);
	Bookable update(Bookable bookable, Locale locale);
    
}
