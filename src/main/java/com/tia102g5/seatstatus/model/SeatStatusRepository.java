package com.tia102g5.seatstatus.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus, Integer> {

	@Query(value = "SELECT seatStatusID FROM seatstatus " + "WHERE activityTimeSlotID = ?1 "
			+ "AND seatID = ?2", nativeQuery = true)
	Integer findSeatStatusByActivityTimeSlotIdAndSeatId(Integer activityTimeSlotId, Integer seatId);
}
