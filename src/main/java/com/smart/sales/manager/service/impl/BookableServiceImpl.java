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

import com.smart.sales.manager.entity.model.Bookable;
import com.smart.sales.manager.exception.model.CustomAuthenticationException;
import com.smart.sales.manager.repository.BookablesRepository;
import com.smart.sales.manager.request.model.Constants;
import com.smart.sales.manager.service.BookableService;

@Service(value = "bookableService")
public class BookableServiceImpl implements BookableService {



	@Autowired
	private BookablesRepository bookableRepository;
	

    @Autowired
    private MessageSource messageSource;

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
	public Bookable save(Bookable bookable, String username) {
		if(null!=bookable.getBusiness()&&null!=bookable.getBusiness().getOwner()&&bookable.getBusiness().getOwner().equals(username))
		return bookableRepository.save(bookable);
		else {
			//String args[]= {"Business details"};
			//String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException("You can't update resources which is not belong to you.");
		}
	}
	

	@Override
	public Bookable update(Bookable bookable,String username,Locale locale) {
		System.out.println(bookable.toString());
		
		Bookable bookableDO = findById(bookable.getId());
		System.out.println(bookableDO.toString());
		if (bookableDO != null && null!=bookable.getBusiness()&&null!=bookable.getBusiness().getOwner()&&bookable.getBusiness().getOwner().equals(username)&&bookableDO.getBusiness().getOwner().equals(username)) {
			BeanUtils.copyProperties(bookable, bookableDO, "isActive", "username", "id", "email", "mobile");
			
			return bookableRepository.save(bookableDO);
		}else {
			String args[]= {"Bookable details"};
			String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException(message);
		}
		
	}
	@Override
	public Bookable update(Bookable bookable,Locale locale) {
		System.out.println(bookable.toString());
		
		Bookable bookableDO = findById(bookable.getId());
		System.out.println(bookableDO.toString());
		if (bookableDO != null && null!=bookable.getBusiness()&&null!=bookable.getBusiness().getOwner()) {
			BeanUtils.copyProperties(bookable, bookableDO, "username", "id", "email", "mobile");			
			return bookableRepository.save(bookableDO);
		}else {
			String args[]= {"Bookable details"};
			String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException(message);
		}
		
	}

	@Override
	public List<Bookable> findByBusinessId(long businessId, String username) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.ASC, "id");		
		Pageable request = PageRequest.of(0, Constants.PAGESIZE, sort);
		//List<Bookable> list = new ArrayList<>();
		return bookableRepository.findByBusinessId(businessId);
		//		return list;
		
	}

	

	

	

}
