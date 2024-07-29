package com.tia102g5.activitytimeslot.model;

import java.io.Serializable;
import java.sql.Date;

//活動時段
public class ActivityTimeSlotVO implements Serializable{
	
	private Integer activityTimeSlotID; //時段ID
	private Integer activityID; //活動ID
	private Date activityTimeSlotDate; //日期
	private Integer activityTimeSlot; //時段
	private Integer activityTimeSlotSeatAmount; //時段剩餘座位數
	
	public ActivityTimeSlotVO() {
		super();
	}

	public ActivityTimeSlotVO(Integer activityTimeSlotID, Integer activityID, Date activityTimeSlotDate,
			Integer activityTimeSlot, Integer activitytimeSlotStatus, Integer activityTimeSlotSeatAmount) {
		super();
		this.activityTimeSlotID = activityTimeSlotID;
		this.activityID = activityID;
		this.activityTimeSlotDate = activityTimeSlotDate;
		this.activityTimeSlot = activityTimeSlot;
		this.activityTimeSlotSeatAmount = activityTimeSlotSeatAmount;
	}

	public Integer getActivityTimeSlotID() {
		return activityTimeSlotID;
	}

	public void setActivityTimeSlotID(Integer activityTimeSlotID) {
		this.activityTimeSlotID = activityTimeSlotID;
	}

	public Integer getActivityID() {
		return activityID;
	}

	public void setActivityID(Integer activityID) {
		this.activityID = activityID;
	}

	public Date getActivityTimeSlotDate() {
		return activityTimeSlotDate;
	}

	public void setActivityTimeSlotDate(Date activityTimeSlotDate) {
		this.activityTimeSlotDate = activityTimeSlotDate;
	}

	public Integer getActivityTimeSlot() {
		return activityTimeSlot;
	}

	public void setActivityTimeSlot(Integer activityTimeSlot) {
		this.activityTimeSlot = activityTimeSlot;
	}

	public Integer getActivityTimeSlotSeatAmount() {
		return activityTimeSlotSeatAmount;
	}

	public void setActivityTimeSlotSeatAmount(Integer activityTimeSlotSeatAmount) {
		this.activityTimeSlotSeatAmount = activityTimeSlotSeatAmount;
	}
	
}
