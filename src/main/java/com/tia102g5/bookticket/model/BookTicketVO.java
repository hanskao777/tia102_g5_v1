package com.tia102g5.bookticket.model;

import java.io.Serializable;
import java.sql.Timestamp;

//票券訂單
public class BookTicketVO implements Serializable{

	private Integer bookTicketID; //票券訂單ID
	private Integer memberID; //會員ID (買家)
	private Integer activityID; //活動ID
	private Integer activityTimeSlotID; //時段ID
	private Integer couponID; //優惠券類型ID
	private Timestamp bookTime; //訂購日期
	private Integer ticketQuantity; //數量
	private Integer totalPrice; //總金額
	
	public BookTicketVO() {
		super();
	}

	public BookTicketVO(Integer bookTicketID, Integer memberID, Integer activityID, Integer activityTimeSlotID,
			Integer couponID, Timestamp bookTime, Integer ticketQuantity, Integer totalPrice) {
		super();
		this.bookTicketID = bookTicketID;
		this.memberID = memberID;
		this.activityID = activityID;
		this.activityTimeSlotID = activityTimeSlotID;
		this.couponID = couponID;
		this.bookTime = bookTime;
		this.ticketQuantity = ticketQuantity;
		this.totalPrice = totalPrice;
	}

	public Integer getBookTicketID() {
		return bookTicketID;
	}

	public void setBookTicketID(Integer bookTicketID) {
		this.bookTicketID = bookTicketID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getActivityID() {
		return activityID;
	}

	public void setActivityID(Integer activityID) {
		this.activityID = activityID;
	}

	public Integer getActivityTimeSlotID() {
		return activityTimeSlotID;
	}

	public void setActivityTimeSlotID(Integer activityTimeSlotID) {
		this.activityTimeSlotID = activityTimeSlotID;
	}

	public Integer getCouponID() {
		return couponID;
	}

	public void setCouponID(Integer couponID) {
		this.couponID = couponID;
	}

	public Timestamp getBookTime() {
		return bookTime;
	}

	public void setBookTime(Timestamp bookTime) {
		this.bookTime = bookTime;
	}

	public Integer getTicketQuantity() {
		return ticketQuantity;
	}

	public void setTicketQuantity(Integer ticketQuantity) {
		this.ticketQuantity = ticketQuantity;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
