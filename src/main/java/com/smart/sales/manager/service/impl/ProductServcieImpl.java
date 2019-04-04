package com.smart.sales.manager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.stereotype.Service;

import com.smart.sales.manager.entity.model.Product;
import com.smart.sales.manager.exception.model.CustomAuthenticationException;
import com.smart.sales.manager.repository.ProductsRepository;
import com.smart.sales.manager.request.model.Constants;
import com.smart.sales.manager.service.ProductService;
import com.smart.sales.manager.service.UserService;

@Service(value = "productService")
public class ProductServcieImpl implements ProductService {



	@Autowired
	private ProductsRepository productRepository;
	

	@Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

	@Override
	public List<Product> findAll(int page, String order, String field) {
		Sort sort = null;
		if (order.equalsIgnoreCase("DESC")) {
			sort = new Sort(Direction.DESC, field);
		} else {
			sort = new Sort(Direction.ASC, field);
		}
		PageRequest request = PageRequest.of(page - 1, Constants.PAGESIZE, sort);
		List<Product> list = new ArrayList<>();
		productRepository.findAll(request).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		productRepository.deleteById(id);
	}

	
	@Override
	public Product findById(long id) {
		return productRepository.findById(id).get();
	}

	

	@Override
	public Product save(Product product, String username) {
		if(null!=product.getBusiness()&&product.getBusiness().getOwnerId()==(userService.findOne(username).getId()))
		return productRepository.save(product);
		else {
			//String args[]= {"Business details"};
			//String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException("You can't update resources which is not belong to you.");
		}
	}
	

	@Override
	public Product update(Product product,String username,Locale locale) {
		System.out.println(product.toString());
		
		Product productDO = findById(product.getId());
		System.out.println(productDO.toString());
		if (productDO != null && null!=product.getBusiness()&&product.getBusiness().getOwnerId()==productDO.getBusiness().getOwnerId()) {
			BeanUtils.copyProperties(product, productDO, "isActive", "username", "id", "email", "mobile");
			
			return productRepository.save(productDO);
		}else {
			String args[]= {"Product details"};
			String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException(message);
		}
		
	}
	@Override
	public Product update(Product product,Locale locale) {
		System.out.println(product.toString());
		
		Product productDO = findById(product.getId());
		System.out.println(productDO.toString());
		if (productDO != null && null!=product.getBusiness()) {
			BeanUtils.copyProperties(product, productDO, "username", "id", "email", "mobile");			
			return productRepository.save(productDO);
		}else {
			String args[]= {"Product details"};
			String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException(message);
		}
		
	}

	@Override
	public List<Product> findByBusinessId(long businessId, String username) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.ASC, "id");		
		Pageable request = PageRequest.of(0, Constants.PAGESIZE, sort);
		//List<Product> list = new ArrayList<>();
		return productRepository.findByBusinessId(businessId);
		//		return list;
		
	}

	

	

	

}
