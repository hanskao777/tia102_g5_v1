package com.tia102g5.ticket.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//票券
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticketID", updatable = false)
	private Integer ticketID; //票券ID
	
	@Column(name = "memberID")
	private Integer memberID; //會員ID (擁有者)
	
	@Column(name = "seatStatusID")
	private Integer seatStatusID; //座位狀態ID
	
	@Column(name = "activityAreaPriceID")
	private Integer activityAreaPriceID; //票價
	
	@Column(name = "bookTicketID")
	private Integer bookTicketID; //票券訂單ID
	
	@Column(name = "activityTimeSlotID")
	private Integer activityTimeSlotID; //時段ID
	
	public Ticket() {
		super();
	}

	public Ticket(Integer ticketID, Integer memberID, Integer seatStatusID, Integer activityAreaPriceID,
			Integer bookTicketID, Integer activityTimeSlotID) {
		super();
		this.ticketID = ticketID;
		this.memberID = memberID;
		this.seatStatusID = seatStatusID;
		this.activityAreaPriceID = activityAreaPriceID;
		this.bookTicketID = bookTicketID;
		this.activityTimeSlotID = activityTimeSlotID;
	}

	public Integer getTicketID() {
		return ticketID;
	}

	public void setTicketID(Integer ticketID) {
		this.ticketID = ticketID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getSeatStatusID() {
		return seatStatusID;
	}

	public void setSeatStatusID(Integer seatStatusID) {
		this.seatStatusID = seatStatusID;
	}

	public Integer getActivityAreaPriceID() {
		return activityAreaPriceID;
	}

	public void setActivityAreaPriceID(Integer activityAreaPriceID) {
		this.activityAreaPriceID = activityAreaPriceID;
	}

	public Integer getBookTicketID() {
		return bookTicketID;
	}

	public void setBookTicketID(Integer bookTicketID) {
		this.bookTicketID = bookTicketID;
	}

	public Integer getActivityTimeSlotID() {
		return activityTimeSlotID;
	}

	public void setActivityTimeSlotID(Integer activityTimeSlotID) {
		this.activityTimeSlotID = activityTimeSlotID;
	}
	
}
