package com.smart.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.smart.sales.manager.entity.model.Bookable;
import com.smart.sales.manager.response.model.ApiResponse;
import com.smart.sales.manager.service.BookableService;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BookableController {

    @Autowired
    private BookableService bookableService;
    
    @Autowired
    private MessageSource messageSource;
  
    @PostMapping(value="/bookable/add-bookable")
    public ApiResponse<Bookable> addNewBookable(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody Bookable bookable, Authentication authentication){
    	String username= authentication.getName();
    	String[] args= {"Bookable details"};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.created",args , locale),bookableService.save(bookable,username));
        
    }
   
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/bookable/{page}/{order}/{field}")
    public ApiResponse<List<Bookable>> getAllBookable(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int page, @PathVariable String order, @PathVariable String field){
    	String[] args= {"Bookables"};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.list.fetched", args, locale),bookableService.findAll(page, order, field));
    }
    
   
    
    @PutMapping("/bookable/me")
    public ApiResponse<Bookable> updateMyBookable(@RequestHeader(name="Accept-language",required=false) Locale locale,@RequestBody Bookable bookable,Authentication authentication) {
    	String username=authentication.getName();
    	String[] args= {"Bookable details"};
   		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),bookableService.update(bookable,username, locale));
    	
    }
    
    @GetMapping("/bookable/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Bookable> getOne(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable long id){
    	Bookable bookable=bookableService.findById(id);
    	String[] args= {"Bookable details"};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),bookable);
    }
    @GetMapping("/business/{id}/bookable")
    public ApiResponse<Bookable> getMyBookableByBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale,Authentication authentication, @PathVariable long id) {
    	String username=authentication.getName();
    	String[] args= {"Bookable details"};
   		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),bookableService.findByBusinessId(id,username));    	
    }
    
    @PutMapping("/bookable")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Bookable> update(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody Bookable bookable) {
    	
    	String[] args= {"Bookable details"};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),bookableService.update(bookable,locale));
    }

    @DeleteMapping("/bookable/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id) {
    	String[] args= {"Specified Bookable"};
    	bookableService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }

    @PutMapping("/bookable/{id}")
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<Void> deleteBookable(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id, Authentication authentication) {
    	authentication.getName();
    	String[] args= {"Specified Bookable"};
    	bookableService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }
}
