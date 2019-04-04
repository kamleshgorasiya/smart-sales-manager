package com.smart.sales.manager.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.smart.sales.manager.entity.model.Booking;




@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByBusinessId(long businessId);
	List<Booking> findByUserId(long userId);
	List<Booking> findByEmployeeId(long employeeId);
	Booking findById(long id);

	
}

