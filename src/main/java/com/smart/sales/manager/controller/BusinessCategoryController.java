package com.smart.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.smart.sales.manager.SSMApplication;
import com.smart.sales.manager.entity.model.Business;
import com.smart.sales.manager.entity.model.BusinessCategory;
import com.smart.sales.manager.entity.model.BusinessCategoryDto;
import com.smart.sales.manager.response.model.ApiResponse;
import com.smart.sales.manager.service.BusinessCategoryService;
import com.smart.sales.manager.service.BusinessService;
import com.smart.sales.manager.service.UserService;

import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BusinessCategoryController {

    @Autowired
    private BusinessCategoryService businessCategoryService;  
  

    @Autowired
    private MessageSource messageSource;
  
    @PostMapping(value="/business-category")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<BusinessCategory> addNewBusinessCategory(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody BusinessCategory businessCategory, Authentication authentication){
    	
    	String[] args= {messageSource.getMessage("business.category",null , locale)};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.created",args, locale),businessCategoryService.save(businessCategory));
        
    }
   
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(value="/business-category/{page}/{order}/{field}")
    public ApiResponse<List<BusinessCategory>> getAllBusinessCategory(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int page, @PathVariable String order, @PathVariable String field){
    	String[] args= {messageSource.getMessage("business.categories",null , locale)};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.list.fetched", args, locale),businessCategoryService.findAll(SSMApplication.getPageRequest(page, order, field)));
    }
    
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(value="/business-category")
    public ApiResponse<Object> getAllBusinessCategory(@RequestHeader(name="Accept-language",required=false) Locale locale){
    	String[] args= {messageSource.getMessage("business.categories",null , locale)};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.list.fetched", args, locale),businessCategoryService.findAll());
    }
    
    
  
    @GetMapping("/business-category/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<BusinessCategory> getOne(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable long id){    	
    	String[] args= {messageSource.getMessage("business.category",null , locale)};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),businessCategoryService.findById(id));
    }

    @PutMapping("/business-category")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<BusinessCategory> update(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody BusinessCategory businessCategory) {
    	String[] args= {messageSource.getMessage("business.category",null , locale)};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),businessCategoryService.update(businessCategory,locale,args));
    }

    @DeleteMapping("/business-category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id) {
    	String[] args= {"Specified Business"};
    	businessCategoryService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }

    
}
