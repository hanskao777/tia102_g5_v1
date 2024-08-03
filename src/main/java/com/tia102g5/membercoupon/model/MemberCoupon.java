package com.tia102g5.membercoupon.model;

import java.util.Date;
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

import com.tia102g5.bookticket.model.BookTicket;
import com.tia102g5.coupontype.model.CouponType;
import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.orders.model.Orders;

@Entity
@Table(name = "membercoupon")
public class MemberCoupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memberCouponID", updatable = false) // 會員優惠券ID
	private Integer memberCouponID;
	

	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID") // 會員ID
	private GeneralMember generalMember;
	
	
	@ManyToOne
	@JoinColumn(name = "couponTypeID", referencedColumnName = "couponTypeID") // 優惠券類型ID
	private CouponType couponType;
	

	@Column(name = "memberCouponExpirationDate") // 有效期限
	private Date memberCouponExpirationDate;
	
	
	@Column(name = "memberCouponStatus") // 使用狀態 0:未使用 1:已使用
	private Integer memberCouponStatus;
	
	
	@Column(name = "memberCouponCreateTime", updatable = false) // 建立時間
	private Date memberCouponCreateTime;
	
	
	
	@OneToOne(mappedBy = "memberCoupon")
	private Orders orders;
	
	
	@OneToOne(mappedBy = "memberCoupon")
	private BookTicket bookTicket;
	
	

	public GeneralMember getGeneralMember() {
		return generalMember;
	}

	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}

	public MemberCoupon() {
		super();
	}

	public Integer getMemberCouponID() {
		return memberCouponID;
	}

	public void setMemberCouponID(Integer memberCouponID) {
		this.memberCouponID = memberCouponID;
	}

	public CouponType getCouponType() {
		return couponType;
	}

	public void setCouponType(CouponType couponType) {
		this.couponType = couponType;
	}

	public Date getMemberCouponExpirationDate() {
		return memberCouponExpirationDate;
	}

	public void setMemberCouponExpirationDate(Date memberCouponExpirationDate) {
		this.memberCouponExpirationDate = memberCouponExpirationDate;
	}

	public Integer getMemberCouponStatus() {
		return memberCouponStatus;
	}

	public void setMemberCouponStatus(Integer memberCouponStatus) {
		this.memberCouponStatus = memberCouponStatus;
	}

	public Date getMemberCouponCreateTime() {
		return memberCouponCreateTime;
	}

	public void setMemberCouponCreateTime(Date memberCouponCreateTime) {
		this.memberCouponCreateTime = memberCouponCreateTime;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public BookTicket getBookTicket() {
		return bookTicket;
	}

	public void setBookTicket(BookTicket bookTicket) {
		this.bookTicket = bookTicket;
	}

//	@Override
//	public String toString() {
//		return "MemberCoupon [memberCouponID=" + memberCouponID + ", generalMember=" + generalMember + ", couponType="
//				+ couponType + ", memberCouponExpirationDate=" + memberCouponExpirationDate + ", memberCouponStatus="
//				+ memberCouponStatus + ", memberCouponCreateTime=" + memberCouponCreateTime + ", orders=" + orders
//				+ ", bookTicket=" + bookTicket + "]";
//	}

	
}
