package com.smart.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.smart.sales.manager.entity.model.Business;
import com.smart.sales.manager.response.model.ApiResponse;
import com.smart.sales.manager.service.BusinessService;
import com.smart.sales.manager.service.UserService;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BusinessController {

    @Autowired
    private BusinessService businessService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;
  
    @PostMapping(value="/business/add-business")
    public ApiResponse<Business> addNewBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale, @Valid @RequestBody Business business, Authentication authentication){
    	String username= authentication.getName();
    	business.setOwnerName(username);
    	business.setOwnerId(userService.findOne(username).getId());
    	String[] args= {messageSource.getMessage("business.details",null , locale)};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.created",args , locale),businessService.save(business));
        
    }
   
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/business/{page}/{order}/{field}")
    public ApiResponse<List<Business>> getAllBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int page, @PathVariable String order, @PathVariable String field){
    	String[] args= {messageSource.getMessage("business.details",null , locale)};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.list.fetched", args, locale),businessService.findAll(page, order, field));
    }
    
    @GetMapping("business/my")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<Business> getMyBusinesses(@RequestHeader(name="Accept-language",required=false) Locale locale, Authentication authentication){
    	String username= authentication.getName();
    	String[] args= {messageSource.getMessage("business.details",null , locale)};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),businessService.findByOwner(username));
    }
    
    @PutMapping("/business/update-my-business")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<Business> updateMyBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale,@RequestBody Business business,Authentication authentication) {
    	String username= authentication.getName();
    	String[] args= {messageSource.getMessage("business.details",null , locale)};
    		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),businessService.update(business,username,locale));    	
    }
    @GetMapping("/business/business-on-off/{id}/{status}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ApiResponse<Business> makeBusinesOnOff(@RequestHeader(name="Accept-language",required=false) Locale locale,@PathVariable long id, @PathVariable boolean status,Authentication authentication) {
    	String username= authentication.getName();
    	String[] args= {messageSource.getMessage("business.status",null , locale)};
    		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),businessService.updateBusinessOnOff(id,status,username,locale));    	
    }
    
    @GetMapping("/business/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Business> getOne(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable long id){
    	Business business=businessService.findById(id);
    	String[] args= {messageSource.getMessage("business.details",null , locale)};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),business);
    }

    @PutMapping("/business")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Business> update(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody Business business) {
    	String[] args= {messageSource.getMessage("business.details",null , locale)};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),businessService.update(business));
    }

    @DeleteMapping("/business/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id) {

    	String[] args= {messageSource.getMessage("the.business",null , locale)};
    	businessService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }

    @PutMapping("/business/{id}")
    public ApiResponse<Void> softDeleteBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id, Authentication authentication) {
    	String name=authentication.getName();
    	String[] args= {messageSource.getMessage("the.business",null , locale)};
    	businessService.deleteByIdAndOwnerId(id,name,true);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }
}
