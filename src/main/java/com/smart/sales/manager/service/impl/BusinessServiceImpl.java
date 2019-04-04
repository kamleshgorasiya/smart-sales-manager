package com.smart.sales.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.smart.sales.manager.entity.model.Business;
import com.smart.sales.manager.exception.model.CustomAuthenticationException;
import com.smart.sales.manager.repository.BusinessRepository;
import com.smart.sales.manager.request.model.Constants;
import com.smart.sales.manager.service.BusinessService;
import java.util.*;

@Service(value = "businessService")
public class BusinessServiceImpl implements BusinessService {



	@Autowired
	private BusinessRepository businessRepository;
	
    @Autowired
    private MessageSource messageSource;
	

	@Override
	public List<Business> findAll(int page, String order, String field) {
		Sort sort = null;
		if (order.equalsIgnoreCase("DESC")) {
			sort = new Sort(Direction.DESC, field);
		} else {
			sort = new Sort(Direction.ASC, field);
		}
		PageRequest request = PageRequest.of(page - 1, Constants.PAGESIZE, sort);
		List<Business> list = new ArrayList<>();
		businessRepository.findAll(request).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		businessRepository.deleteById(id);
	}

	
	@Override
	public void deleteByIdAndOwnerId(long id,String owner_name,boolean isDeleted) {
		businessRepository.updateIsDeleted(isDeleted,new Date().getTime(),id,owner_name);
	}
	
	@Override
	public Business findById(long id) {
		return businessRepository.findById(id).get();
	}

	

	@Override
	public Business save(Business business) {
		
		return businessRepository.save(business);
	}
	

	@Override
	public Business update(Business business, String ownerName, Locale locale) {
		System.out.println(business.toString());
	
		if (business != null&&business.getOwnerId()>0&&business.getId()>0) {
			businessRepository.update(business, business.getId(), business.getOwnerId(),business.getBusinessCategory().getId(),ownerName);
			return businessRepository.findById(business.getId()).get();
		}
		else {
			//String args[]= {"Business details"};
			String[] args= {messageSource.getMessage("business.details",null , locale)};
			String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException(message);
		}
		
	}
	
	@Override
	public Business updateBusinessOnOff(long id, boolean status, String ownerName, Locale locale) {
			Business business=businessRepository.findById(id).get();
		if (business != null&&business.getOwnerId()>0&&business.getId()>0) {
			businessRepository.updateBusinessOnOff(id,status,ownerName, new Date().getTime());		
			business.setIsOn(status);
			return business;
		}
		else {
			//String args[]= {"Business details"};
			String[] args= {messageSource.getMessage("business.status",null , locale)};
			String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException(message);
		}
		
	}

	@Override
	public Business update(Business business) {
		System.out.println(business.toString());		
		
		Business businessDbo = findById(business.getId());
		System.out.println(businessDbo.toString());
		if (businessDbo != null) {
			BeanUtils.copyProperties(business, businessDbo, "username", "id", "email", "mobile","created","updated");			
			return businessRepository.save(businessDbo);
		}
		return businessDbo;
	}
	@Override
	public List<Business> findByName(String businessName) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.ASC, "id");		
		PageRequest request = PageRequest.of(0, Constants.PAGESIZE, sort);
		List<Business> list = new ArrayList<>();
		businessRepository.findByName(businessName,request).iterator().forEachRemaining(list::add);
		
		return list;
		
	}

	@Override
	public List<Business> findByZipcode(String zipcode) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.ASC, "id");		
		PageRequest request = PageRequest.of(0, Constants.PAGESIZE, sort);
		List<Business> list = new ArrayList<>();
		businessRepository.findByZipcode(zipcode,request).iterator().forEachRemaining(list::add);		
		return list;
	}

	@Override
	public List<Business> findByBusinessCategory(String category) {
		Sort sort = new Sort(Direction.ASC, "id");		
		PageRequest request = PageRequest.of(0, Constants.PAGESIZE, sort);
		List<Business> list = new ArrayList<>();
		businessRepository.findByBusinessCategory(category,request).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public List<Business> findByOwner(String ownerName) {
	
		List<Business> list = new ArrayList<>();
		businessRepository.findByOwnerName(ownerName).iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public Business findByIdAndOwner(long id, long owner) {
		return businessRepository.findByIdAndOwnerId(id,owner);
	}

	

	

}
