package com.smart.sales.manager.service;

import java.util.List;
import java.util.Locale;

import com.smart.sales.manager.entity.model.Product;


public interface ProductService {

	Product save(Product service,String username);   
    List<Product> findAll(int page, String order,String field);
    void delete(long id);    
    Product findById(long id);
    
    List<Product> findByBusinessId(long businessId, String username);
    Product update(Product product, String username, Locale locale);
    Product update(Product product, Locale locale);
    
}
