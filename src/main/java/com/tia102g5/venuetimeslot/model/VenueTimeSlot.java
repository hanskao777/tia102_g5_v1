package com.tia102g5.venuetimeslot.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tia102g5.seatstatus.model.SeatStatus;
import com.tia102g5.venuerental.model.VenueRental;

@Entity
@Table(name = "VenueTimeSlot")
public class VenueTimeSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int venueTimeSlotID;

	@ManyToOne
	@JoinColumn(name = "venueRentalID")
	private VenueRental venueRental;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date venueTimeSlotDate;

	@Column(nullable = false)
	private int venueTimeSlot;

	@Column(nullable = false)
	private int venueTimeSlotStatus;

	@OneToMany(mappedBy = "activityTimeSlot")
	private Set<SeatStatus> seatStatuses;

	public int getVenueTimeSlotID() {
		return venueTimeSlotID;
	}

	public void setVenueTimeSlotID(int venueTimeSlotID) {
		this.venueTimeSlotID = venueTimeSlotID;
	}

	public VenueRental getVenueRental() {
		return venueRental;
	}

	public void setVenueRental(VenueRental venueRental) {
		this.venueRental = venueRental;
	}

	public Date getVenueTimeSlotDate() {
		return venueTimeSlotDate;
	}

	public void setVenueTimeSlotDate(Date venueTimeSlotDate) {
		this.venueTimeSlotDate = venueTimeSlotDate;
	}

	public int getVenueTimeSlot() {
		return venueTimeSlot;
	}

	public void setVenueTimeSlot(int venueTimeSlot) {
		this.venueTimeSlot = venueTimeSlot;
	}

	public int getVenueTimeSlotStatus() {
		return venueTimeSlotStatus;
	}

	public void setVenueTimeSlotStatus(int venueTimeSlotStatus) {
		this.venueTimeSlotStatus = venueTimeSlotStatus;
	}

	public Set<SeatStatus> getSeatStatuses() {
		return seatStatuses;
	}

	public void setSeatStatuses(Set<SeatStatus> seatStatuses) {
		this.seatStatuses = seatStatuses;
	}

	public VenueTimeSlot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VenueTimeSlot(int venueTimeSlotID, VenueRental venueRental, Date venueTimeSlotDate, int venueTimeSlot,
			int venueTimeSlotStatus, Set<SeatStatus> seatStatuses) {
		super();
		this.venueTimeSlotID = venueTimeSlotID;
		this.venueRental = venueRental;
		this.venueTimeSlotDate = venueTimeSlotDate;
		this.venueTimeSlot = venueTimeSlot;
		this.venueTimeSlotStatus = venueTimeSlotStatus;
		this.seatStatuses = seatStatuses;
	}

	@Override
	public String toString() {
		return "VenueTimeSlot [venueTimeSlotID=" + venueTimeSlotID + ", venueRental=" + venueRental
				+ ", venueTimeSlotDate=" + venueTimeSlotDate + ", venueTimeSlot=" + venueTimeSlot
				+ ", venueTimeSlotStatus=" + venueTimeSlotStatus + ", seatStatuses=" + seatStatuses + "]";
	}

}