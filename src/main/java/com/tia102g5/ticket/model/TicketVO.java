package com.tia102g5.ticket.model;

import java.io.Serializable;

//票券
public class TicketVO implements Serializable{

	private Integer ticketID; //票券ID
	private Integer memberID; //會員ID (擁有者)
	private Integer seatStatusID; //座位狀態ID
	private Integer activityAreaPriceID; //票價
	private Integer bookTicketID; //票券訂單ID
	private Integer activityTimeSlotID; //時段ID
	
	public TicketVO() {
		super();
	}

	public TicketVO(Integer ticketID, Integer memberID, Integer seatStatusID, Integer activityAreaPriceID,
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
