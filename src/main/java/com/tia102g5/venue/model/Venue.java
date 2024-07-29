package com.tia102g5.venue.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tia102g5.seat.model.Seat;
import com.tia102g5.venuearea.model.VenueArea;
import com.tia102g5.venuerental.model.VenueRental;

@Entity
@Table(name = "venue")
public class Venue {//49行oneToMany關聯到宜倫的Activity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venueID", updatable = false)
	int venueID;

	@Column(name = "venueName", nullable = false, length = 255)
	String venueName;

	@Column(name = "venuePhone", nullable = false, length = 255)
	String venuePhone;

	@Column(name = "venueContactPerson", nullable = false, length = 255)
	String venueContactPerson;

	@Column(name = "venueAddress", nullable = false, length = 255)
	String venueAddress;

	@Column(name = "venueLocation", nullable = false, length = 255)
	String venueLocation;

	@OneToMany(mappedBy = "venue")
	private Set<VenueArea> venueAreas;

	@OneToMany(mappedBy = "venue")
	private Set<Seat> seats;

	@OneToMany(mappedBy = "venue")
	private Set<VenueRental> venueRentals;

//	@OneToMany(mappedBy = "venue")
//	private Set<Activity> activitys;

	public int getVenueID() {
		return venueID;
	}

	public void setVenueID(int venueID) {
		this.venueID = venueID;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getVenuePhone() {
		return venuePhone;
	}

	public void setVenuePhone(String venuePhone) {
		this.venuePhone = venuePhone;
	}

	public String getVenueContactPerson() {
		return venueContactPerson;
	}

	public void setVenueContactPerson(String venueContactPerson) {
		this.venueContactPerson = venueContactPerson;
	}

	public String getVenueAddress() {
		return venueAddress;
	}

	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	public String getVenueLocation() {
		return venueLocation;
	}

	public void setVenueLocation(String venueLocation) {
		this.venueLocation = venueLocation;
	}

	public Set<VenueArea> getVenueAreas() {
		return venueAreas;
	}

	public void setVenueAreas(Set<VenueArea> venueAreas) {
		this.venueAreas = venueAreas;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	public Set<VenueRental> getVenueRentals() {
		return venueRentals;
	}

	public void setVenueRentals(Set<VenueRental> venueRentals) {
		this.venueRentals = venueRentals;
	}

	public Venue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venue(int venueID, String venueName, String venuePhone, String venueContactPerson, String venueAddress,
			String venueLocation, Set<VenueArea> venueAreas, Set<Seat> seats, Set<VenueRental> venueRentals) {
		super();
		this.venueID = venueID;
		this.venueName = venueName;
		this.venuePhone = venuePhone;
		this.venueContactPerson = venueContactPerson;
		this.venueAddress = venueAddress;
		this.venueLocation = venueLocation;
		this.venueAreas = venueAreas;
		this.seats = seats;
		this.venueRentals = venueRentals;
	}

//	@Override
//	public String toString() {
//		return "Venue [venueID=" + venueID + ", venueName=" + venueName + ", venuePhone=" + venuePhone
//				+ ", venueContactPerson=" + venueContactPerson + ", venueAddress=" + venueAddress + ", venueLocation="
//				+ venueLocation + ", venueAreas=" + venueAreas + ", seats=" + seats + ", venueRentals=" + venueRentals
//				+ "]";
//	}

}
