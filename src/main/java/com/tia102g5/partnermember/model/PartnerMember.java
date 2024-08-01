package com.tia102g5.partnermember.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.venuerental.model.VenueRental;

@Entity
@Table(name = "partnermember")
public class PartnerMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "partnerID", updatable = false) // "廠商ID"
	private Integer partnerID;
	
	@Column(name = "taxID") // "統一編號(登入帳號)"
	private String taxID;
	
	@Column(name = "partnerName") // "公司名稱"
	private String partnerName;
	
	@Column(name = "partnerHeading") // "抬頭"
	private String partnerHeading;
	
	@Column(name = "partnerAddress") // "地址"
	private String partnerAddress;
	
	@Column(name = "partnerPhone") // "連絡電話"
	private String partnerPhone;
	
	@Column(name = "partnerContactPerson") // "聯絡人"
	private String partnerContactPerson;
	
	@Column(name = "partnerPassword") // "密碼"
	private String partnerPassword;
	
	@Column(name = "partnerEmail") // "電子信箱"
	private String partnerEmail;
	
	
	@Column(name = "partnerCreateTime", updatable = false) // "帳號建立時間"
	private Date partnerCreateTime;
	
	@Column(name = "partnerAccountStatus") // "帳號狀態 0:黑名單 1.使用中 2.申請中"
	private Integer partnerAccountStatus;
	
	
//	@OneToMany(mappedBy = partnerMember, cascade = CascadeType.ALL)
//	@OrderBy(commodityID asc)
//	private Set<Commodity> commodity;
	
//	@OneToMany(mappedBy = partnerMember, cascade = CascadeType.ALL)
//	@OrderBy(activityID asc)
//	private Set<Activity> activity;
	
//	@OneToMany(mappedBy = partnerMember, cascade = CascadeType.ALL)
//	@OrderBy(venueRentalID asc)
//	private Set<VenueRental> venueRental;

	public Integer getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(Integer partnerID) {
		this.partnerID = partnerID;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerHeading() {
		return partnerHeading;
	}

	public void setPartnerHeading(String partnerHeading) {
		this.partnerHeading = partnerHeading;
	}

	public String getPartnerAddress() {
		return partnerAddress;
	}

	public void setPartnerAddress(String partnerAddress) {
		this.partnerAddress = partnerAddress;
	}

	public String getPartnerPhone() {
		return partnerPhone;
	}

	public void setPartnerPhone(String partnerPhone) {
		this.partnerPhone = partnerPhone;
	}

	public String getPartnerContactPerson() {
		return partnerContactPerson;
	}

	public void setPartnerContactPerson(String partnerContactPerson) {
		this.partnerContactPerson = partnerContactPerson;
	}

	public String getPartnerPassword() {
		return partnerPassword;
	}

	public void setPartnerPassword(String partnerPassword) {
		this.partnerPassword = partnerPassword;
	}

	public String getPartnerEmail() {
		return partnerEmail;
	}

	public void setPartnerEmail(String partnerEmail) {
		this.partnerEmail = partnerEmail;
	}

	public Date getPartnerCreateTime() {
		return partnerCreateTime;
	}
	

	public void setPartnerCreateTime(Date partnerCreateTime) {
		this.partnerCreateTime = partnerCreateTime;
	}

	public Integer getPartnerAccountStatus() {
		return partnerAccountStatus;
	}

	public void setPartnerAccountStatus(Integer partnerAccountStatus) {
		this.partnerAccountStatus = partnerAccountStatus;
	}
	
	

	@Override
	public String toString() {
		return "Partner [partnerID=" + partnerID + ", taxID=" + taxID + ", partnerName=" + partnerName
				+ ", partnerHeading=" + partnerHeading + ", partnerAddress=" + partnerAddress + ", partnerPhone="
				+ partnerPhone + ", partnerContactPerson=" + partnerContactPerson + ", partnerPassword="
				+ partnerPassword + ", partnerEmail=" + partnerEmail + ", partnerCreateTime=" + partnerCreateTime
				+ ", partnerAccountStatus=" + partnerAccountStatus + "]";
	}
	
	
}
