package com.tia102g5.venuerental.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRentalRepository extends JpaRepository<VenueRental,Integer>{
	
	 List<VenueRental> findByPartnerMemberPartnerID(Integer partnerID);
}
