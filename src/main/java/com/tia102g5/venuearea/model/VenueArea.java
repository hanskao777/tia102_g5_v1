package com.tia102g5.venuearea.model;

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

import com.tia102g5.activityareaprice.model.ActivityAreaPrice;
import com.tia102g5.seat.model.Seat;
import com.tia102g5.venue.model.Venue;

@Entity
@Table(name = "VenueArea")
public class VenueArea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int venueAreaID;

	@ManyToOne
	@JoinColumn(name = "venueID", nullable = false)
	private Venue venue;

	@Column(nullable = false, length = 255)
	private String venueAreaName;

	@OneToMany(mappedBy = "venueArea")
	private Set<Seat> seats;

	@OneToMany(mappedBy = "venueArea")
	private Set<ActivityAreaPrice> activityAreaPrices;

	public int getVenueAreaID() {
		return venueAreaID;
	}

	public void setVenueAreaID(int venueAreaID) {
		this.venueAreaID = venueAreaID;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public String getVenueAreaName() {
		return venueAreaName;
	}

	public void setVenueAreaName(String venueAreaName) {
		this.venueAreaName = venueAreaName;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public Set<ActivityAreaPrice> getActivityAreaPrices() {
		return activityAreaPrices;
	}

	public void setActivityAreaPrices(Set<ActivityAreaPrice> activityAreaPrices) {
		this.activityAreaPrices = activityAreaPrices;
	}

	public VenueArea() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VenueArea(int venueAreaID, Venue venue, String venueAreaName, Set<Seat> seats,
			Set<ActivityAreaPrice> activityAreaPrices) {
		super();
		this.venueAreaID = venueAreaID;
		this.venue = venue;
		this.venueAreaName = venueAreaName;
		this.seats = seats;
		this.activityAreaPrices = activityAreaPrices;
	}

//	@Override
//	public String toString() {
//		return "VenueArea [venueAreaID=" + venueAreaID + ", venue=" + venue + ", venueAreaName=" + venueAreaName
//				+ ", seats=" + seats + ", activityAreaPrices=" + activityAreaPrices + "]";
//	}

}
