package com.tia102g5.venuerental.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VenueRentalRepository extends JpaRepository<VenueRental,Integer>{
	
	 List<VenueRental> findByPartnerMemberPartnerID(Integer partnerID);
	 
	 //查詢 (未新增活動)
	 @Query(value = "SELECT vr.*, a.* FROM VenueRental vr JOIN Activity a ON vr.venueRentalID = a.venueRentalID WHERE vr.partnerID = ?1 AND a.activityStatus = 0", nativeQuery = true)
	 List<VenueRental> findUnNewByPartnerID(Integer partnerID);
	 
}
