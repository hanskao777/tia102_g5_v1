package com.tia102g5.venuetimeslot.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tia102g5.venuerental.model.VenueRental;

@Entity
@Table(name = "venuetimeslot")
public class VenueTimeSlot implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venueTimeSlotID")
	private int venueTimeSlotID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venueRentalID", referencedColumnName = "venueRentalID")
	private VenueRental venueRental;

	@Column(name = "venueTimeSlotDate")
	private Date venueTimeSlotDate;

	@Column(name = "venueTimeSlot")
	private int venueTimeSlot;

	@Column(name = "venueTimeSlotStatus")
	private int venueTimeSlotStatus;

	public VenueTimeSlot() {
	}

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

}