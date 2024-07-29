package com.tia102g5.coupon.model;

import java.io.Serializable;
import java.math.BigDecimal;

//優惠券
public class CouponVO implements Serializable{
	
	private Integer couponID; //優惠券類型ID
	private String couponName; //優惠券名稱
	private String couponType; //優惠券類型
	private String couponRegulation; //使用規則
	private BigDecimal couponDiscount; //折扣數
	
	public CouponVO() {
		super();
	}

	public CouponVO(Integer couponID, String couponName, String couponType, String couponRegulation,
			BigDecimal couponDiscount) {
		super();
		this.couponID = couponID;
		this.couponName = couponName;
		this.couponType = couponType;
		this.couponRegulation = couponRegulation;
		this.couponDiscount = couponDiscount;
	}

	public Integer getCouponID() {
		return couponID;
	}

	public void setCouponID(Integer couponID) {
		this.couponID = couponID;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getCouponRegulation() {
		return couponRegulation;
	}

	public void setCouponRegulation(String couponRegulation) {
		this.couponRegulation = couponRegulation;
	}

	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	
}
