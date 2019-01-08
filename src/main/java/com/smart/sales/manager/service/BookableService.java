package com.smart.sales.manager.service;

import java.util.List;

import com.smart.sales.manager.entity.model.Bookable;


public interface BookableService {

    Bookable save(Bookable service);   
    List<Bookable> findAll(int page, String order,String field);
    void delete(long id);    
    Bookable findById(long id);
    Bookable update(Bookable service);
    List<Bookable> findByBusiness_Id(long businessId);
    
}
