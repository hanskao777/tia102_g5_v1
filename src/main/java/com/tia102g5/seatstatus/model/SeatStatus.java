package com.tia102g5.seatstatus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tia102g5.activitytimeslot.model.ActivityTimeSlot;
import com.tia102g5.seat.model.Seat;
import com.tia102g5.ticket.model.Ticket;

@Entity
@Table(name = "seatstatus")
public class SeatStatus implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seatStatusID")
	private int seatStatusID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "activityTimeSlotID", referencedColumnName = "activityTimeSlotID", nullable = false)
	private ActivityTimeSlot activityTimeSlot;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seatID", referencedColumnName = "seatID", nullable = false)
	private Seat seat;

	@Column(name = "seatStatus", nullable = false)
	private int seatStatus;

	@OneToOne(mappedBy = "seatStatus", fetch = FetchType.LAZY)
	private Ticket ticket;

	public SeatStatus() {
	}

	public int getSeatStatusID() {
		return seatStatusID;
	}

	public void setSeatStatusID(int seatStatusID) {
		this.seatStatusID = seatStatusID;
	}

	public ActivityTimeSlot getActivityTimeSlot() {
		return activityTimeSlot;
	}

	public void setActivityTimeSlot(ActivityTimeSlot activityTimeSlot) {
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

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}