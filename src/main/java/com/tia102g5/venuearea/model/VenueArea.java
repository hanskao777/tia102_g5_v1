package com.tia102g5.venuearea.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "venuearea")
public class VenueArea implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venueAreaID", updatable = false)
	private Integer venueAreaID;  // 修改為 Integer

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venueID", referencedColumnName = "venueID")
	private Venue venue;

	@Column(name = "venueAreaName", length = 255)
	private String venueAreaName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venueArea")
	private Set<Seat> seats;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venueArea")
	private Set<ActivityAreaPrice> activityAreaPrices;

	public VenueArea() {
	}

	public Integer getVenueAreaID() {  // 修改為 Integer
		return venueAreaID;
	}

	public void setVenueAreaID(Integer venueAreaID) {  // 修改為 Integer
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

}
