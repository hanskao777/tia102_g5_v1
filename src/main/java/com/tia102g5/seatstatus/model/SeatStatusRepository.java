package com.tia102g5.seatstatus.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus, Integer> {

	@Query(value = "SELECT * FROM seatStatus " + "WHERE activityTimeSlotID = ?1 "
			+ "AND seatID = ?2", nativeQuery = true)
	SeatStatus findSeatStatusByActivityTimeSlotIdAndSeatId(Integer activityTimeSlotId, Integer seatId);

	@Query(value = "SELECT * FROM seatstatus WHERE activityTimeSlotID = ?1", nativeQuery = true)
	List<SeatStatus> findAllByActivityTimeSlotID(Integer activityTimeSlotID);

	//為了在seatSelectPage顯示不可選取的座位而寫的方法)
	@Query(value = "SELECT s.seatName FROM seat s " + "JOIN seatStatus ss ON s.seatID = ss.seatID "
			+ "WHERE ss.activityTimeSlotID = ?1 " + "AND ss.seatStatus IN (?2, ?3)", nativeQuery = true)
	List<String> findUnavailableSeatNamesByActivityTimeSlotID(Integer activityTimeSlotID, Integer status1,
			Integer status2);
}
