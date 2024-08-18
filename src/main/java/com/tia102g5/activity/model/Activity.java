package com.tia102g5.activity.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.tia102g5.activityPicture.model.ActivityPicture;
import com.tia102g5.activityareaprice.model.ActivityAreaPrice;
import com.tia102g5.activitycollection.model.ActivityCollection;
import com.tia102g5.activitytimeslot.model.ActivityTimeSlot;
import com.tia102g5.bookticket.model.BookTicket;
import com.tia102g5.commodity.model.Commodity;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "venueRentalID", referencedColumnName = "venueRentalID")
	private VenueRental venueRental; // 場館申請資料

	@Column(name = "activityName")
	@NotEmpty(message = "請輸入活動名稱")
	private String activityName; // 名稱

	@Column(name = "activityContent", columnDefinition = "text")
	private String activityContent; // 內容

	@Column(name = "activityCreateTime", updatable = false, insertable = false)
	private Timestamp activityCreateTime; // 建立時間

	@Column(name = "activityPostTime")
//	@Future(message="日期必須是在今日(不含)之後")
//	@NotNull(message = "請選擇排程時間")
	private Date activityPostTime; // 排程時間

	@Column(name = "activityTag")
	@NotNull(message = "請選擇類型標籤")
	private String activityTag; // 類型標籤

	@Column(name = "activityStatus")
	private Integer activityStatus = 0; // 設定狀態 0:未設定 1:已設定

	@Column(name = "ticketSetStatus")
	private Integer ticketSetStatus = 0; // 票券設定狀態 0:未設定 1:已設定

	@Column(name = "sellTime")
//	@Future(message="日期必須是在今日(不含)之後")
//	@NotNull(message = "請選擇起售日")
	private Date sellTime; // 起售日

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("activityAreaPriceID asc")
	private Set<ActivityAreaPrice> activityAreaPrices; // 活動區域價格

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("activityPictureID asc")
	private Set<ActivityPicture> activityPictures = new HashSet<ActivityPicture>(); // 活動圖片

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("activityCollectionID asc")
	private Set<ActivityCollection> activityCollections; // 活動收藏

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("activityTimeSlotID asc")
//	@NotNull(message = "請選擇活動時段")
	private Set<ActivityTimeSlot> activityTimeSlots; // 活動時段

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("bookTicketID asc")
	private Set<BookTicket> bookTickets; // 票券訂單

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
	@OrderBy("commodityID asc")
	private Set<Commodity> commodities; // 商品

	// 建構子
	public Activity() {
		super();
	}

	public Activity(Integer activityID, PartnerMember partnerMember, Venue venue, VenueRental venueRental,
			String activityName, String activityContent, Timestamp activityCreateTime, Date activityPostTime,
			String activityTag, Integer activityStatus, Integer ticketSetStatus, Date sellTime,
			Set<ActivityAreaPrice> activityAreaPrices, Set<ActivityPicture> activityPictures,
			Set<ActivityCollection> activityCollections, Set<ActivityTimeSlot> activityTimeSlots,
			Set<BookTicket> bookTickets, Set<Commodity> commodities) {
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
		this.activityAreaPrices = activityAreaPrices;
		this.activityPictures = activityPictures;
		this.activityCollections = activityCollections;
		this.activityTimeSlots = activityTimeSlots;
		this.bookTickets = bookTickets;
		this.commodities = commodities;
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

	public Set<ActivityAreaPrice> getActivityAreaPrices() {
		return activityAreaPrices;
	}

	public void setActivityAreaPrices(Set<ActivityAreaPrice> activityAreaPrices) {
		this.activityAreaPrices = activityAreaPrices;
	}

	public Set<ActivityPicture> getActivityPictures() {
		return activityPictures;
	}

	public void setActivityPictures(Set<ActivityPicture> activityPictures) {
		this.activityPictures = activityPictures;
	}

	public Set<ActivityCollection> getActivityCollections() {
		return activityCollections;
	}

	public void setActivityCollections(Set<ActivityCollection> activityCollections) {
		this.activityCollections = activityCollections;
	}

	public Set<ActivityTimeSlot> getActivityTimeSlots() {
		return activityTimeSlots;
	}

	public void setActivityTimeSlots(Set<ActivityTimeSlot> activityTimeSlots) {
		this.activityTimeSlots = activityTimeSlots;
	}

	public Set<BookTicket> getBookTickets() {
		return bookTickets;
	}

	public void setBookTickets(Set<BookTicket> bookTickets) {
		this.bookTickets = bookTickets;
	}

	public Set<Commodity> getCommodities() {
		return commodities;
	}

	public void setCommodities(Set<Commodity> commodities) {
		this.commodities = commodities;
	}
	
}