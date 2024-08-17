package com.tia102g5.seatstatus.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatStatusService {

	@Autowired
	private SeatStatusRepository seatStatusRepository;

	public SeatStatus getSeatStatusByActivityTimeSlotIdAndSeatId(Integer activityTimeSlotId, Integer seatId) {
		return seatStatusRepository.findSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotId, seatId);
	}

	public List<SeatStatus> getAllSeatStatusByActivityTimeSlotID(Integer activityTimeSlotID) {
		return seatStatusRepository.findAllByActivityTimeSlotID(activityTimeSlotID);
	}

	// 為了在seatSelectPage顯示不可選取的座位而寫的方法)
	public List<String> getUnavailableSeatNames(Integer activityTimeSlotID) {
		// 假設狀態 2 和 3 表示座位不可用
		return seatStatusRepository.findUnavailableSeatNamesByActivityTimeSlotID(activityTimeSlotID, 2, 3);
	}
}
