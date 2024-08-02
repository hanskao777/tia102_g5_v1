package com.tia102g5.bookticket.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.activitytimeslot.model.ActivityTimeSlot;
import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.membercoupon.model.MemberCoupon;
import com.tia102g5.ticket.model.Ticket;

//票券訂單
@Entity
@Table(name = "bookticket")
public class BookTicket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookTicketID", updatable = false)
	private Integer bookTicketID; // 票券訂單ID

	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
	private GeneralMember generalMember; // 一般會員 (買家)

	@ManyToOne
	@JoinColumn(name = "activityID", referencedColumnName = "activityID")
	private Activity activity; // 活動

	@ManyToOne
	@JoinColumn(name = "activityTimeSlotID", referencedColumnName = "activityTimeSlotID")
	private ActivityTimeSlot activityTimeSlot; // 活動時段

	@OneToOne
	@JoinColumn(name = "memberCouponID")
	private MemberCoupon memberCoupon; // 會員優惠券

	@Column(name = "bookTime", updatable = false, insertable = false)
	private Timestamp bookTime; // 訂購日期

	@Column(name = "ticketQuantity")
	private Integer ticketQuantity; // 數量

	@Column(name = "totalPrice")
	private Integer totalPrice; // 總金額
	
	@OneToMany(mappedBy = "bookTicket", cascade = CascadeType.ALL)
	@OrderBy("ticketID asc")
	private Set<Ticket> tickets; // 票券

	// 建構子
	public BookTicket() {
		super();
	}

	public BookTicket(Integer bookTicketID, GeneralMember generalMember, Activity activity,
			ActivityTimeSlot activityTimeSlot, MemberCoupon memberCoupon, Timestamp bookTime, Integer ticketQuantity,
			Integer totalPrice, Set<Ticket> tickets) {
		super();
		this.bookTicketID = bookTicketID;
		this.generalMember = generalMember;
		this.activity = activity;
		this.activityTimeSlot = activityTimeSlot;
		this.memberCoupon = memberCoupon;
		this.bookTime = bookTime;
		this.ticketQuantity = ticketQuantity;
		this.totalPrice = totalPrice;
		this.tickets = tickets;
	}

	// Getters & Setters
	public Integer getBookTicketID() {
		return bookTicketID;
	}

	public void setBookTicketID(Integer bookTicketID) {
		this.bookTicketID = bookTicketID;
	}

	public GeneralMember getGeneralMember() {
		return generalMember;
	}

	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public ActivityTimeSlot getActivityTimeSlot() {
		return activityTimeSlot;
	}

	public void setActivityTimeSlot(ActivityTimeSlot activityTimeSlot) {
		this.activityTimeSlot = activityTimeSlot;
	}

	public MemberCoupon getMemberCoupon() {
		return memberCoupon;
	}

	public void setMemberCoupon(MemberCoupon memberCoupon) {
		this.memberCoupon = memberCoupon;
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

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}
