package com.tia102g5.venue.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tia102g5.seat.model.Seat;

public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
