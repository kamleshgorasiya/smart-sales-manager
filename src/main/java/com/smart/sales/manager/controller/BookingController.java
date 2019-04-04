package com.smart.sales.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.smart.sales.manager.SSMApplication;
import com.smart.sales.manager.entity.model.Booking;
import com.smart.sales.manager.response.model.ApiResponse;
import com.smart.sales.manager.service.BookingService;

import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private MessageSource messageSource;
  
    @PostMapping(value="/booking/add-booking")
    public ApiResponse<Booking> addNewBooking(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody Booking booking, Authentication authentication){
    	String username= authentication.getName();
    	String[] args= {"Booking details"};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.created",args , locale),bookingService.save(booking,username));
        
    }
   
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/booking/{page}/{order}/{field}")
    public ApiResponse<Page<Booking>> getAllBooking(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int page, @PathVariable String order, @PathVariable String field){
    	String[] args= {"Bookings"};
    	return new ApiResponse<Page<Booking>>(HttpStatus.OK.value(), messageSource.getMessage("entity.list.fetched", args, locale),bookingService.findAll(SSMApplication.getPageRequest(page, order, field)));
    }
    
   
    
    @PutMapping("/booking/me")
    public ApiResponse<Booking> updateMyBooking(@RequestHeader(name="Accept-language",required=false) Locale locale,@RequestBody Booking booking,Authentication authentication) {
    	String username=authentication.getName();
    	String[] args= {"Booking details"};
   		return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),bookingService.update(booking,username, locale));
    	
    }
    
    @GetMapping("/booking/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Booking> getOne(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable long id){
    	Booking booking=bookingService.findById(id);
    	String[] args= {"Booking details"};
    	return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),booking);
    }
    @GetMapping("/business/{id}/booking")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<Booking>> getMyBookingByBusiness(@RequestHeader(name="Accept-language",required=false) Locale locale,Authentication authentication, @PathVariable long id) {
    	String username=authentication.getName();
    	String[] args= {"Booking details"};
   		return new ApiResponse<List<Booking>>(HttpStatus.OK.value(), messageSource.getMessage("entity.fetched", args, locale),bookingService.findByBusinessId(id,username));    	
    }
    
    @PutMapping("/booking")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Booking> update(@RequestHeader(name="Accept-language",required=false) Locale locale, @RequestBody Booking booking) {
    	
    	String[] args= {"Booking details"};
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.updated", args, locale),bookingService.update(booking,locale));
    }

    @DeleteMapping("/booking/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> delete(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id) {
    	String[] args= {"Specified Booking"};
    	bookingService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }

    @PutMapping("/booking/{id}")
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<Void> deleteBooking(@RequestHeader(name="Accept-language",required=false) Locale locale, @PathVariable int id, Authentication authentication) {
    	authentication.getName();
    	String[] args= {"Specified Booking"};
    	bookingService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), messageSource.getMessage("entity.deleted", args, locale), null);
    }
}
