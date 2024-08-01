package com.tia102g5.activity.model;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.tia102g5.activityPicture.model.ActivityPicture;
import com.tia102g5.activityareaprice.model.ActivityAreaPrice;
import com.tia102g5.activitycollection.model.ActivityCollection;
import com.tia102g5.activitytimeslot.model.ActivityTimeSlot;
import com.tia102g5.bookticket.model.BookTicket;
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

	@Column(name = "activityCreateTime", updatable = false, insertable = false)
	private Timestamp activityCreateTime; // 建立時間

	@Column(name = "activityPostTime")
	@Future(message="日期必須是在今日(不含)之後")
	private Date activityPostTime; // 排程時間

	@Column(name = "activityTag")
	private String activityTag; // 類型標籤

	@Column(name = "activityStatus")
	private Integer activityStatus; // 設定狀態 0:未設定 1:已設定

	@Column(name = "ticketSetStatus")
	private Integer ticketSetStatus; // 票券設定狀態 0:未設定 1:已設定

	@Column(name = "sellTime")
	@Future(message="日期必須是在今日(不含)之後")
	private Date sellTime; // 起售日

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("activityAreaPriceID asc")
	private Set<ActivityAreaPrice> activityAreaPrice; // 活動區域價格

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("activityPictureID asc")
	private Set<ActivityPicture> activityPicture; // 活動圖片

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("activityCollectionID asc")
	private Set<ActivityCollection> activityCollection; // 活動收藏

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("activityTimeSlotID asc")
	private Set<ActivityTimeSlot> activityTimeSlot; // 活動時段

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("bookTicketID asc")
	private Set<BookTicket> bookTicket; // 票券訂單

//	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
//	@OrderBy("commodityID asc")
//	private Set<Commodity> commodity; // 商品

	// 建構子
	public Activity() {
		super();
	}

	public Activity(Integer activityID, PartnerMember partnerMember, Venue venue, VenueRental venueRental,
			String activityName, String activityContent, Timestamp activityCreateTime, Date activityPostTime,
			String activityTag, Integer activityStatus, Integer ticketSetStatus, Date sellTime,
			Set<ActivityAreaPrice> activityAreaPrice, Set<ActivityPicture> activityPicture,
			Set<ActivityCollection> activityCollection, Set<ActivityTimeSlot> activityTimeSlot,
			Set<BookTicket> bookTicket/* , Set<Commodity> commodity */) {
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
		this.activityAreaPrice = activityAreaPrice;
		this.activityPicture = activityPicture;
		this.activityCollection = activityCollection;
		this.activityTimeSlot = activityTimeSlot;
		this.bookTicket = bookTicket;
//		this.commodity = commodity;
	}

	// Getters & Setters
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

	public Set<ActivityAreaPrice> getActivityAreaPrice() {
		return activityAreaPrice;
	}

	public void setActivityAreaPrice(Set<ActivityAreaPrice> activityAreaPrice) {
		this.activityAreaPrice = activityAreaPrice;
	}

	public Set<ActivityPicture> getActivityPicture() {
		return activityPicture;
	}

	public void setActivityPicture(Set<ActivityPicture> activityPicture) {
		this.activityPicture = activityPicture;
	}

	public Set<ActivityCollection> getActivityCollection() {
		return activityCollection;
	}

	public void setActivityCollection(Set<ActivityCollection> activityCollection) {
		this.activityCollection = activityCollection;
	}

	public Set<ActivityTimeSlot> getActivityTimeSlot() {
		return activityTimeSlot;
	}

	public void setActivityTimeSlot(Set<ActivityTimeSlot> activityTimeSlot) {
		this.activityTimeSlot = activityTimeSlot;
	}

	public Set<BookTicket> getBookTicket() {
		return bookTicket;
	}

	public void setBookTicket(Set<BookTicket> bookTicket) {
		this.bookTicket = bookTicket;
	}

//	public Set<Commodity> getCommodity() {
//		return commodity;
//	}
//
//	public void setCommodity(Set<Commodity> commodity) {
//		this.commodity = commodity;
//	}
}