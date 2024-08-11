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
	private Integer venueTimeSlotID;  // 修改為 Integer

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venueRentalID", referencedColumnName = "venueRentalID")
	private VenueRental venueRental;

	@Column(name = "venueTimeSlotDate")
	private Date venueTimeSlotDate;

	@Column(name = "venueTimeSlot")
	private Integer venueTimeSlot;  // 修改為 Integer

	@Column(name = "venueTimeSlotStatus")
	private Integer venueTimeSlotStatus;  // 修改為 Integer

	public VenueTimeSlot() {
	}

	public Integer getVenueTimeSlotID() {  // 修改為 Integer
		return venueTimeSlotID;
	}

	public void setVenueTimeSlotID(Integer venueTimeSlotID) {  // 修改為 Integer
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

	public Integer getVenueTimeSlot() {  // 修改為 Integer
		return venueTimeSlot;
	}

	public void setVenueTimeSlot(Integer venueTimeSlot) {  // 修改為 Integer
		this.venueTimeSlot = venueTimeSlot;
	}

	public Integer getVenueTimeSlotStatus() {  // 修改為 Integer
		return venueTimeSlotStatus;
	}

	public void setVenueTimeSlotStatus(Integer venueTimeSlotStatus) {  // 修改為 Integer
		this.venueTimeSlotStatus = venueTimeSlotStatus;
	}

}
