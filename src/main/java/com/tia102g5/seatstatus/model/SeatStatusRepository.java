package com.tia102g5.seatstatus.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tia102g5.seat.model.Seat;

public interface SeatStatusRepository extends JpaRepository<SeatStatus, Integer> {

}
