package com.smart.sales.manager.service;


import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.smart.sales.manager.entity.model.Booking;


public interface BookingService {

	
	List<Booking> findByUserId(long userId);
	List<Booking> findByEmployeeId(long employeeId);
	Booking findById(long id);
	
	Booking update(Booking booking, Locale locale);
	List<Booking> findByBusinessId(long businessId, String username);
	void delete(long id);
	Page<Booking> findAll(PageRequest request);
	Booking save(Booking booking, String username);
	Booking update(Booking booking, String username, Locale locale);

	
}

