package com.tia102g5.venuerental.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VenueRentalRepository extends JpaRepository<VenueRental,Integer>{
	
	 List<VenueRental> findByPartnerMemberPartnerID(Integer partnerID);
	 
	 //查詢 (未新增活動)
	 @Query(value = "SELECT * FORM venueRental WHERE venueRentalStatus = 1 AND partnerID = ?", nativeQuery = true)
	 List<VenueRental> findUnNewByPartnerID(Integer partnerID);
	 
}
