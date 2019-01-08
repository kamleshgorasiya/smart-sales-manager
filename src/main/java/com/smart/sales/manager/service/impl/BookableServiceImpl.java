package com.smart.sales.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.stereotype.Service;

import com.smart.sales.manager.entity.model.Bookable;
import com.smart.sales.manager.repository.BookablesRepository;
import com.smart.sales.manager.request.model.Constants;
import com.smart.sales.manager.service.BookableService;

@Service(value = "serviceBookable")
public class BookableServiceImpl implements BookableService {



	@Autowired
	private BookablesRepository bookableRepository;
	

	@Override
	public List<Bookable> findAll(int page, String order, String field) {
		Sort sort = null;
		if (order.equalsIgnoreCase("DESC")) {
			sort = new Sort(Direction.DESC, field);
		} else {
			sort = new Sort(Direction.ASC, field);
		}
		PageRequest request = PageRequest.of(page - 1, Constants.PAGESIZE, sort);
		List<Bookable> list = new ArrayList<>();
		bookableRepository.findAll(request).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		bookableRepository.deleteById(id);
	}

	
	@Override
	public Bookable findById(long id) {
		return bookableRepository.findById(id).get();
	}

	

	@Override
	public Bookable save(Bookable business) {
		
		return bookableRepository.save(business);
	}
	

	@Override
	public Bookable update(Bookable business) {
		System.out.println(business.toString());
		
		Bookable businessDbo = findById(business.getId());
		System.out.println(businessDbo.toString());
		if (businessDbo != null) {
			BeanUtils.copyProperties(business, businessDbo, "isActive", "username", "id", "email", "mobile");
			
			return bookableRepository.save(businessDbo);
		}
		return businessDbo;
	}

	@Override
	public List<Bookable> findByBusiness_Id(long businessId) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.ASC, "id");		
		PageRequest request = PageRequest.of(0, Constants.PAGESIZE, sort);
		//List<Bookable> list = new ArrayList<>();
		return bookableRepository.findByBusiness_Id(businessId,request);
		//		return list;
		
	}

	

	

	

}
