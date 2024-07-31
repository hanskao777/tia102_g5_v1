package com.tia102g5.coupontype.model;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//優惠券
@Entity
@Table(name = "coupon")
public class CouponType implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "couponTypeID", updatable = false)
	private Integer couponTypeID; //優惠券類型ID
	
	@Column(name = "couponTypeName")
	private String couponTypeName; //優惠券名稱
		
	@Column(name = "couponTypeRegulation", columnDefinition = "text")
	private String couponTypeRegulation; //使用規則
	
	@Column(name = "couponTypeDiscount")
	private BigDecimal couponTypeDiscount; //折扣數
	
	public CouponType() {
		super();
	}

	public CouponType(Integer couponTypeID, String couponTypeName, String couponTypeRegulation,
			BigDecimal couponTypeDiscount) {
		super();
		this.couponTypeID = couponTypeID;
		this.couponTypeName = couponTypeName;
		this.couponTypeRegulation = couponTypeRegulation;
		this.couponTypeDiscount = couponTypeDiscount;
	}

	public Integer getCouponTypeID() {
		return couponTypeID;
	}

	public void setCouponTypeID(Integer couponTypeID) {
		this.couponTypeID = couponTypeID;
	}

	public String getCouponTypeName() {
		return couponTypeName;
	}

	public void setCouponTypeName(String couponTypeName) {
		this.couponTypeName = couponTypeName;
	}

	public String getCouponTypeRegulation() {
		return couponTypeRegulation;
	}

	public void setCouponTypeRegulation(String couponTypeRegulation) {
		this.couponTypeRegulation = couponTypeRegulation;
	}

	public BigDecimal getCouponTypeDiscount() {
		return couponTypeDiscount;
	}

	public void setCouponTypeDiscount(BigDecimal couponTypeDiscount) {
		this.couponTypeDiscount = couponTypeDiscount;
	}
	
}
