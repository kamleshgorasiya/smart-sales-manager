package com.smart.sales.manager.service;

import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.PageRequest;

import com.smart.sales.manager.entity.model.BusinessCategory;
import com.smart.sales.manager.entity.model.BusinessCategoryDto;
import com.smart.sales.manager.entity.model.User;
import com.smart.sales.manager.request.model.UserDto;

public interface BusinessCategoryService {



	void delete(long id);

	BusinessCategory findById(long id);

	BusinessCategory update(BusinessCategory businessCategory, Locale locale, String[] args);

	List<BusinessCategory> findAll(PageRequest pageRequest);
	Iterable<BusinessCategory> findAll();


	BusinessCategory save(BusinessCategory businessCategory);

   
}
