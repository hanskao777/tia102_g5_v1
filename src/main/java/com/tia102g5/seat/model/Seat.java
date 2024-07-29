package com.tia102g5.seat.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tia102g5.seatstatus.model.SeatStatus;
import com.tia102g5.venue.model.Venue;
import com.tia102g5.venuearea.model.VenueArea;

@Entity
@Table(name = "Seat")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatID;

	@ManyToOne
	@JoinColumn(name = "venueID", nullable = false)
	private Venue venue;

	@ManyToOne
	@JoinColumn(name = "venueAreaID", nullable = false)
	private VenueArea venueArea;

	@Column(nullable = false, length = 255)
	private String seatName;

	@Column(nullable = false)
	private int seatRow;

	@Column(nullable = false)
	private int seatNumber;

	@OneToMany(mappedBy = "seat")
	private Set<SeatStatus> seatStatuses;

	public int getSeatID() {
		return seatID;
	}

	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public VenueArea getVenueArea() {
		return venueArea;
	}

	public void setVenueArea(VenueArea venueArea) {
		this.venueArea = venueArea;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Set<SeatStatus> getSeatStatuses() {
		return seatStatuses;
	}

	public void setSeatStatuses(Set<SeatStatus> seatStatuses) {
		this.seatStatuses = seatStatuses;
	}

	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seat(int seatID, Venue venue, VenueArea venueArea, String seatName, int seatRow, int seatNumber,
			Set<SeatStatus> seatStatuses) {
		super();
		this.seatID = seatID;
		this.venue = venue;
		this.venueArea = venueArea;
		this.seatName = seatName;
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.seatStatuses = seatStatuses;
	}

	@Override
	public String toString() {
		return "Seat [seatID=" + seatID + ", venue=" + venue + ", venueArea=" + venueArea + ", seatName=" + seatName
				+ ", seatRow=" + seatRow + ", seatNumber=" + seatNumber + ", seatStatuses=" + seatStatuses + "]";
	}

}
