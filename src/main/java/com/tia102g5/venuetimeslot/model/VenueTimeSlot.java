package com.tia102g5.venuetimeslot.model;

import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tia102g5.seatstatus.model.SeatStatus;
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

	@Column(name = "venueTimeSlotDate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date venueTimeSlotDate;

	@Column(name = "venueTimeSlot", nullable = false)
	private int venueTimeSlot;

	@Column(name = "venueTimeSlotStatus", nullable = false)
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