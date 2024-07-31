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
import com.tia102g5.generalmember.model.GeneralMember;

@Entity
@Table(name = "MemberCoupon")
public class MemberCoupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memberCouponID", updatable = false) // 會員優惠券ID
	private Integer memberCouponID;
	
//	@Column(name = "memberID") // 會員ID
//	private Integer memberID;
	
	
	@Column(name = "couponTypeID") // 優惠券類型ID
	private Integer couponTypeID;
	

	@Column(name = "memberCouponExpirationDate") // 有效期限
	private Date memberCouponExpirationDate;
	
	
	@Column(name = "memberCouponStatus") // 使用狀態 0:未使用 1:已使用
	private Integer memberCouponStatus;
	
	
	@Column(name = "memberCouponCreateTime", updatable = false) // 建立時間
	private Date memberCouponCreateTime;
	
	
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
	private GeneralMember generalmember;
//	
//	@ManyToOne
//	@JoinColumn(name = "couponTypeID", referencedColumnName = "couponID")
//	private CouponType coupontype;
	
//	@OneToOne(mappedBy = "membercoupon", cascade = CascadeType.ALL)
//	private Orders orders;
	
//	@OneToOne(mappedBy = "membercoupon", cascade = CascadeType.ALL)
//	private BookTicket bookTicket;
	
	

	public GeneralMember getGeneralmember() {
		return generalmember;
	}

	public void setGeneralmember(GeneralMember generalmember) {
		this.generalmember = generalmember;
	}

	public MemberCoupon() {
		super();
	}

	public MemberCoupon(Integer memberCouponID, Integer memberID, Integer couponTypeID, Date memberCouponExpirationDate,
			Integer memberCouponStatus, Date memberCouponCreateTime) {
		super();
		this.memberCouponID = memberCouponID;
//		this.memberID = memberID;
		this.couponTypeID = couponTypeID;
		this.memberCouponExpirationDate = memberCouponExpirationDate;
		this.memberCouponStatus = memberCouponStatus;
		this.memberCouponCreateTime = memberCouponCreateTime;
	}

	public Integer getMemberCouponID() {
		return memberCouponID;
	}

	public void setMemberCouponID(Integer memberCouponID) {
		this.memberCouponID = memberCouponID;
	}

//	public Integer getMemberID() {
//		return memberID;
//	}
//
//	public void setMemberID(Integer memberID) {
//		this.memberID = memberID;
//	}

	public Integer getCouponTypeID() {
		return couponTypeID;
	}

	public void setCouponTypeID(Integer couponTypeID) {
		this.couponTypeID = couponTypeID;
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

	@Override
	public String toString() {
		return "MemberCoupon [memberCouponID=" + memberCouponID + ", couponTypeID=" + couponTypeID
				+ ", memberCouponExpirationDate=" + memberCouponExpirationDate + ", memberCouponStatus="
				+ memberCouponStatus + ", memberCouponCreateTime=" + memberCouponCreateTime + ", generalmember="
				+ generalmember + "]";
	}

	
	
}
