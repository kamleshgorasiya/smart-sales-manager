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
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private MessageSource messageSource;
  
    @PostMapping(value="/business/add-business")
    public ApiResponse<Business> addNewBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody Business business, Authentication authentication){
    	String username= authentication.getName();
    	business.setOwner(username);
    	String[] args= {"Business details"};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.created",args , locale),businessService.save(business));
        
    }
   
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/business/{page}/{order}/{field}")
    public ApiResponse<List<Business>> getAllBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int page, @PathVariable String order, @PathVariable String field){
    	String[] args= {"Businesses"};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.list.fetched", args, locale),businessService.findAll(page, order, field));
    }
    
    @GetMapping("business/my")
    public ApiResponse<Business> getMyBusinesses(@RequestHeader(name="Accept-language",required=false) Locale locale, Authentication authentication){
    	String username= authentication.getName();
    	String[] args= {"Your Business details"};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),businessService.findByOwner(username));
    }
    
    @PutMapping("/business/me")
    public ApiResponse<Business> updateMyBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale,@RequestBody Business business,Authentication authentication) {
    	String username= authentication.getName();
    	String[] args= {"Business details"};
    		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),businessService.update(business,username,locale));
    	
    }
    
    @GetMapping("/business/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Business> getOne(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable long id){
    	Business business=businessService.findById(id);
    	String[] args= {"Business details"};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),business);
    }

    @PutMapping("/business")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Business> update(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody Business business) {
    	String[] args= {"Business details"};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),businessService.update(business));
    }

    @DeleteMapping("/business/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id) {
    	String[] args= {"Specified Business"};
    	businessService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }

    @PutMapping("/business/{id}")
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<Void> deleteBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id, Authentication authentication) {
    	String name=authentication.getName();
    	String[] args= {"Specified Business"};
    	businessService.deleteBusinessByIdAndOwner(id,name);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }
}
