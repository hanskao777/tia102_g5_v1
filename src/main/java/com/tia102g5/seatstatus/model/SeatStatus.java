package com.tia102g5.seatstatus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tia102g5.seat.model.Seat;
import com.tia102g5.venuetimeslot.model.VenueTimeSlot;

@Entity
@Table(name = "SeatStatus")
public class SeatStatus {//31行關聯到宜倫的ticket
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatStatusID;

	@ManyToOne
	@JoinColumn(name = "activityTimeSlotID", nullable = false)
	private VenueTimeSlot activityTimeSlot;

	@ManyToOne
	@JoinColumn(name = "seatID", nullable = false)
	private Seat seat;
	
//	@OneToOne
	@Column(nullable = false)
	private int seatStatus;

	public int getSeatStatusID() {
		return seatStatusID;
	}

	public void setSeatStatusID(int seatStatusID) {
		this.seatStatusID = seatStatusID;
	}

	public VenueTimeSlot getActivityTimeSlot() {
		return activityTimeSlot;
	}

	public void setActivityTimeSlot(VenueTimeSlot activityTimeSlot) {
		this.activityTimeSlot = activityTimeSlot;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public int getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(int seatStatus) {
		this.seatStatus = seatStatus;
	}

	public SeatStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SeatStatus(int seatStatusID, VenueTimeSlot activityTimeSlot, Seat seat, int seatStatus) {
		super();
		this.seatStatusID = seatStatusID;
		this.activityTimeSlot = activityTimeSlot;
		this.seat = seat;
		this.seatStatus = seatStatus;
	}

}