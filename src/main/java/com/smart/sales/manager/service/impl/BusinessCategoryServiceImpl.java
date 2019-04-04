package com.smart.sales.manager.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.smart.sales.manager.entity.model.BusinessCategory;
import com.smart.sales.manager.entity.model.BusinessCategoryDto;
import com.smart.sales.manager.exception.model.CustomAuthenticationException;
import com.smart.sales.manager.repository.BusinessCategoryRepository;
import com.smart.sales.manager.service.BusinessCategoryService;
import java.util.*;

@Service(value = "businessCategoryService")
public class BusinessCategoryServiceImpl implements BusinessCategoryService {



	@Autowired
	private BusinessCategoryRepository businessCategoryRepository;
	
    @Autowired
    private MessageSource messageSource;
	

	@Override
	public List<BusinessCategory> findAll(PageRequest pageRequest) {
		
		List<BusinessCategory> list = new ArrayList<>();
		businessCategoryRepository.findAll(pageRequest).iterator().forEachRemaining(list::add);
		return list;
		
	}
	
	@Override
	public Iterable<BusinessCategory> findAll() {		
		return businessCategoryRepository.findAll();
		//return list;
		
	}

	@Override
	public void delete(long id) {
		businessCategoryRepository.deleteById(id);
	}

	
	
	@Override
	public BusinessCategory findById(long id) {
		return businessCategoryRepository.findById(id).get();
	}

	@Override
	public BusinessCategory save(BusinessCategory businessCategory) {
		return businessCategoryRepository.save(businessCategory);
	}
	

	@Override
	public BusinessCategory update(BusinessCategory businessCategory, Locale locale, String[] args) {
		System.out.println(businessCategory.toString());		
		
		BusinessCategory businessPersistedCategory = findById(businessCategory.getId());
		System.out.println(businessPersistedCategory.toString());
		if (businessPersistedCategory != null&&businessPersistedCategory.getId()==businessCategory.getId()) {
			BeanUtils.copyProperties(businessCategory, businessPersistedCategory,  "id","created","updated");			
			return businessCategoryRepository.save(businessPersistedCategory);
		}
		else {
			String message = messageSource.getMessage("specific.resource.notfound", args, locale);
			throw new CustomAuthenticationException(message);
		}
		
	}
	
	

	
	

	

}
