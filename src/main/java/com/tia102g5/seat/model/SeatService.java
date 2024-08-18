package com.tia102g5.seat.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public Integer findSeatId(Integer venueId, String seatName) {
        Seat seat = seatRepository.findByVenueIdAndSeatName(venueId, seatName);
        return seat != null ? seat.getSeatID() : null;
    }
}