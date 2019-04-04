package com.smart.sales.manager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.stereotype.Service;

import com.smart.sales.manager.entity.model.Booking;
import com.smart.sales.manager.entity.model.Booking;
import com.smart.sales.manager.exception.model.CustomAuthenticationException;

import com.smart.sales.manager.repository.BookingRepository;
import com.smart.sales.manager.request.model.Constants;
import com.smart.sales.manager.service.BookingService;
import com.smart.sales.manager.service.UserService;

@Service(value = "bookingService")
public class BookingServiceImpl implements BookingService {



	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
    private UserService userService;
	
    @Autowired
    private MessageSource messageSource;

	@Override
	public Page<Booking> findAll(PageRequest request) {
		
		List<Booking> list = new ArrayList<>();
		return bookingRepository.findAll(request);//.iterator().forEachRemaining(list::add);
		//return list;
	}

	@Override
	public void delete(long id) {
		bookingRepository.deleteById(id);
	}

	
	@Override
	public Booking findById(long id) {
		return bookingRepository.findById(id);
	}

	

	@Override
	public Booking save(Booking booking, String username) {
		if(null!=booking.getBusiness()&&booking.getBusiness().getOwnerId()==(userService.findOne(username).getId()))
		return bookingRepository.save(booking);
		else {
			//String args[]= {"Business details"};
			//String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException("You can't update resources which is not belong to you.");
		}
	}
	

	@Override
	public Booking update(Booking booking,String username,Locale locale) {
		System.out.println(booking.toString());
		
		Booking bookingDO = findById(booking.getId());
		System.out.println(bookingDO.toString());
		if (bookingDO != null && null!=booking.getBusiness()&&booking.getBusiness().getOwnerId()==bookingDO.getBusiness().getOwnerId()) {
			BeanUtils.copyProperties(booking, bookingDO, "isDeleted", "user_id", "id", "business_id");
			
			return bookingRepository.save(bookingDO);
		}else {
			String args[]= {"Booking details"};
			String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException(message);
		}
		
	}
	@Override
	public Booking update(Booking booking,Locale locale) {
		System.out.println(booking.toString());
		
		Booking bookingDO = findById(booking.getId());
		System.out.println(bookingDO.toString());
		if (bookingDO != null && null!=booking.getBusiness()) {
			
			BeanUtils.copyProperties(booking, bookingDO, "user_id", "id", "business_id");
			return bookingRepository.save(bookingDO);
		}else {
			String args[]= {"Booking details"};
			String message = messageSource.getMessage("entity.updated.error", args, locale);
			throw new CustomAuthenticationException(message);
		}
		
	}

	@Override
	public List<Booking> findByBusinessId(long businessId, String username) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.ASC, "id");		
		Pageable request = PageRequest.of(0, Constants.PAGESIZE, sort);
		//List<Booking> list = new ArrayList<>();
		return bookingRepository.findByBusinessId(businessId);
		//		return list;
		
	}

	
	@Override
	public List<Booking> findByUserId(long userID) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.ASC, "id");		
		Pageable request = PageRequest.of(0, Constants.PAGESIZE, sort);
		//List<Booking> list = new ArrayList<>();
		return bookingRepository.findByUserId(userID);
		//		return list;
		
	}
	
	@Override
	public List<Booking> findByEmployeeId(long userID) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.ASC, "id");		
		Pageable request = PageRequest.of(0, Constants.PAGESIZE, sort);
		//List<Booking> list = new ArrayList<>();
		return bookingRepository.findByEmployeeId(userID);
		//		return list;
		
	}
	

	

	

}
