package com.tia102g5.venuerental.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VenueRentalRepository extends JpaRepository<VenueRental,Integer>{
	
	 List<VenueRental> findByPartnerMemberPartnerID(Integer partnerID);
	 
	 //查詢 (未新增活動)
	 @Query(value = "SELECT vr.*\r\n"
	 		+ "FROM VenueRental vr\r\n"
	 		+ "JOIN Activity a ON vr.venueRentalID = a.venueRentalID\r\n"
	 		+ "WHERE a.activityStatus = 0\r\n"
	 		+ "  AND vr.venueRentalStatus = 1\r\n"
	 		+ "  AND vr.partnerID = ?", nativeQuery = true)
	 List<VenueRental> findUnNewByPartnerID(Integer partnerID);
	 
}
