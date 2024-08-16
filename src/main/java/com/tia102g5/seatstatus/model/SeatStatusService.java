package com.tia102g5.seatstatus.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatStatusService {

	@Autowired
	private SeatStatusRepository seatStatusRepository;

	public SeatStatus getSeatStatusByActivityTimeSlotIdAndSeatId(Integer activityTimeSlotId, Integer seatId) {
		return seatStatusRepository.findSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotId, seatId);
	}
}
