package com.smart.sales.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.sales.manager.entity.model.Business;
import com.smart.sales.manager.entity.model.Role;
import com.smart.sales.manager.entity.model.User;
import com.smart.sales.manager.exception.model.CustomAuthenticationException;
import com.smart.sales.manager.repository.BusinessRepository;
import com.smart.sales.manager.repository.RoleRepository;
import com.smart.sales.manager.repository.UserRepository;
import com.smart.sales.manager.request.model.Constants;
import com.smart.sales.manager.request.model.UserDto;
import com.smart.sales.manager.service.BusinessService;
import com.smart.sales.manager.service.UserService;

import java.nio.file.AccessDeniedException;
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
	public void deleteBusinessByIdAndOwner(long id,String owner) {
		businessRepository.deleteByIdAndOwner(id,owner);
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
		if(!business.getOwner().equals(ownerName))
			{
			String args[]= {"Business details"};
			String message = messageSource.getMessage("entity.updated", args, locale);
			throw new CustomAuthenticationException(message);
			}
			
		
		Business businessDbo = findById(business.getId());
		System.out.println(businessDbo.toString());
		if (businessDbo != null) {
			BeanUtils.copyProperties(business, businessDbo, "isActive", "username", "id", "email", "mobile");
			
			return businessRepository.save(businessDbo);
		}
		return businessDbo;
	}
	@Override
	public Business update(Business business) {
		System.out.println(business.toString());		
		
		Business businessDbo = findById(business.getId());
		System.out.println(businessDbo.toString());
		if (businessDbo != null) {
			BeanUtils.copyProperties(business, businessDbo, "username", "id", "email", "mobile");			
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
	public List<Business> findByOwner(String ownerId) {
		Sort sort = new Sort(Direction.ASC, "id");		
		PageRequest request = PageRequest.of(0, Constants.PAGESIZE, sort);
		List<Business> list = new ArrayList<>();
		businessRepository.findByOwner(ownerId,request).iterator().forEachRemaining(list::add);
		return list;
	}

	

	

}
