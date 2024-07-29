package com.tia102g5.activity.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

//活動
public class ActivityVO implements Serializable {

	private Integer activityID; // 活動ID
	private Integer partnerID; // 廠商ID
	private Integer venueID; // 場館ID
	private Integer venueRentalID; // 申請資料ID
	private String activityName; // 名稱
	private String activityContent; // 內容
	private byte[] activityPicture; // 圖片
	private Timestamp activityCreateTime; // 建立時間
	private Date activityPostTime; // 排程時間
	private String activityTag; // 類型標籤
	private Integer activityStatus; // 設定狀態
	private Integer ticketSetStatus; // 票券設定狀態
	private Date sellTime; // 起售日

	// 建構子
	public ActivityVO() {
		super();
	}

	public ActivityVO(Integer activityID, Integer partnerID, Integer venueID, Integer venueRentalID,
			String activityName, String activityContent, byte[] activityPicture, Timestamp activityCreateTime,
			Date activityPostTime, String activityTag, Integer activityStatus, Integer ticketSetStatus,
			Date sellTime) {
		super();
		this.activityID = activityID;
		this.partnerID = partnerID;
		this.venueID = venueID;
		this.venueRentalID = venueRentalID;
		this.activityName = activityName;
		this.activityContent = activityContent;
		this.activityPicture = activityPicture;
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

	public Integer getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(Integer partnerID) {
		this.partnerID = partnerID;
	}

	public Integer getVenueID() {
		return venueID;
	}

	public void setVenueID(Integer venueID) {
		this.venueID = venueID;
	}

	public Integer getVenueRentalID() {
		return venueRentalID;
	}

	public void setVenueRentalID(Integer venueRentalID) {
		this.venueRentalID = venueRentalID;
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

	public byte[] getActivityPicture() {
		return activityPicture;
	}

	public void setActivityPicture(byte[] activityPicture) {
		this.activityPicture = activityPicture;
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

	@Override
	// 顯示活動資料
	public String toString() {
		String activityShowInfo = "\n活動資料: \n" + "活動ID: " + activityID + "\n" + "廠商ID: " + partnerID + "\n" + "場館ID: "
				+ venueID + "\n" + "申請資料ID:" + venueRentalID + "\n" + "名稱: " + activityName + "\n" + "內容: "
				+ activityContent + "\n" + "圖片: " + (activityPicture != null ? "有圖片" : "沒有圖片") + "\n" + "建立時間: "
				+ activityCreateTime + "\n" + "排程時間: " + activityPostTime + "\n" + "類型標籤: " + activityTag + "\n"
				+ "設定狀態: " + activityStatus + "\n" + "票券設定狀態: " + ticketSetStatus + "\n" + "起售日: " + sellTime + "\n";

		return activityShowInfo;
	}

}