package com.tia102g5.activity.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tia102g5.partnermember.model.PartnerMember;
import com.tia102g5.venue.model.Venue;
import com.tia102g5.venuerental.model.VenueRental;

//活動
@Entity
@Table(name = "activity")
public class Activity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activityID", updatable = false)
	private Integer activityID; // 活動ID
	
	@ManyToOne
	@JoinColumn(name = "partnerID", referencedColumnName = "partnerID")
	private PartnerMember partnerMember; // 廠商
	
	@ManyToOne
	@JoinColumn(name = "venueID", referencedColumnName = "venueID")
	private Venue venue; // 場館
	
	@ManyToOne
	@JoinColumn(name = "venueRentalID", referencedColumnName = "venueRentalID")
	private VenueRental venueRental; // 場館申請資料
	
	@Column(name = "activityName")
	private String activityName; // 名稱
	
	@Column(name = "activityContent", columnDefinition = "text")
	private String activityContent; // 內容
	
	@Column(name = "activityCreateTime")
	private Timestamp activityCreateTime; // 建立時間
	
	@Column(name = "activityPostTime")
	private Date activityPostTime; // 排程時間
	
	@Column(name = "activityTag")
	private String activityTag; // 類型標籤
	
	@Column(name = "activityStatus")
	private Integer activityStatus; // 設定狀態 0:未設定 1:已設定
	
	@Column(name = "ticketSetStatus")
	private Integer ticketSetStatus; // 票券設定狀態 0:未設定 1:已設定
	
	@Column(name = "sellTime")
	private Date sellTime; // 起售日

	// 建構子
	public Activity() {
		super();
	}

	public Activity(Integer activityID, PartnerMember partnerMember, Venue venue, VenueRental venueRental,
			String activityName, String activityContent, Timestamp activityCreateTime, Date activityPostTime,
			String activityTag, Integer activityStatus, Integer ticketSetStatus, Date sellTime) {
		super();
		this.activityID = activityID;
		this.partnerMember = partnerMember;
		this.venue = venue;
		this.venueRental = venueRental;
		this.activityName = activityName;
		this.activityContent = activityContent;
		this.activityCreateTime = activityCreateTime;
		this.activityPostTime = activityPostTime;
		this.activityTag = activityTag;
		this.activityStatus = activityStatus;
		this.ticketSetStatus = ticketSetStatus;
		this.sellTime = sellTime;
	}

	// Getter & Setter
	public Integer getActivityID() {
		return activityID;
	}

	public void setActivityID(Integer activityID) {
		this.activityID = activityID;
	}

	public PartnerMember getPartnerMember() {
		return partnerMember;
	}

	public void setPartnerMember(PartnerMember partnerMember) {
		this.partnerMember = partnerMember;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public VenueRental getVenueRental() {
		return venueRental;
	}

	public void setVenueRental(VenueRental venueRental) {
		this.venueRental = venueRental;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public Timestamp getActivityCreateTime() {
		return activityCreateTime;
	}

	public void setActivityCreateTime(Timestamp activityCreateTime) {
		this.activityCreateTime = activityCreateTime;
	}

	public Date getActivityPostTime() {
		return activityPostTime;
	}

	public void setActivityPostTime(Date activityPostTime) {
		this.activityPostTime = activityPostTime;
	}

	public String getActivityTag() {
		return activityTag;
	}

	public void setActivityTag(String activityTag) {
		this.activityTag = activityTag;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Integer getTicketSetStatus() {
		return ticketSetStatus;
	}

	public void setTicketSetStatus(Integer ticketSetStatus) {
		this.ticketSetStatus = ticketSetStatus;
	}

	public Date getSellTime() {
		return sellTime;
	}

	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}
	
}