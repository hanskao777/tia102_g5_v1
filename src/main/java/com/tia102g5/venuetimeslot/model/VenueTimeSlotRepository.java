package com.tia102g5.venuetimeslot.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tia102g5.seat.model.Seat;

public interface VenueTimeSlotRepository extends JpaRepository<VenueTimeSlot, Integer> {

}
