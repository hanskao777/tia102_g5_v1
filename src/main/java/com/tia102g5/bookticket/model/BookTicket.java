package com.tia102g5.bookticket.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//票券訂單
@Entity
@Table(name = "bookticket")
public class BookTicket implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookTicketID", updatable = false)
	private Integer bookTicketID; //票券訂單ID
	
	@Column(name = "memberID")
	private Integer memberID; //會員ID (買家)
	
	@Column(name = "activityID")
	private Integer activityID; //活動ID
	
	@Column(name = "activityTimeSlotID")
	private Integer activityTimeSlotID; //時段ID
	
	@Column(name = "couponID")
	private Integer couponID; //優惠券類型ID
	
	@Column(name = "bookTime")
	private Timestamp bookTime; //訂購日期
	
	@Column(name = "ticketQuantity")
	private Integer ticketQuantity; //數量
	
	@Column(name = "totalPrice")
	private Integer totalPrice; //總金額
	
	public BookTicket() {
		super();
	}

	public BookTicket(Integer bookTicketID, Integer memberID, Integer activityID, Integer activityTimeSlotID,
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
