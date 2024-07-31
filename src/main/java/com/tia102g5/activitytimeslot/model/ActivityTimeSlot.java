package com.tia102g5.activitytimeslot.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tia102g5.activity.model.Activity;

//活動時段
@Entity
@Table(name = "activitytimeslot")
public class ActivityTimeSlot implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activityTimeSlot", updatable = false)
	private Integer activityTimeSlotID; //時段ID
	
	@ManyToOne
	@JoinColumn(name = "activityID", referencedColumnName = "activityID")
	private Activity activity; // 活動
	
	@Column(name = "activityTimeSlotDate")
	private Date activityTimeSlotDate; //日期
	
	@Column(name = "activityTimeSlot")
	private Integer activityTimeSlot; //時段 1:早 2:午 3:晚
	
	@Column(name = "activityTimeSlotSeatAmount")
	private Integer activityTimeSlotSeatAmount; //時段剩餘座位數
	
	// 建構子
	public ActivityTimeSlot() {
		super();
	}

	public ActivityTimeSlot(Integer activityTimeSlotID, Activity activity, Date activityTimeSlotDate,
			Integer activityTimeSlot, Integer activityTimeSlotSeatAmount) {
		super();
		this.activityTimeSlotID = activityTimeSlotID;
		this.activity = activity;
		this.activityTimeSlotDate = activityTimeSlotDate;
		this.activityTimeSlot = activityTimeSlot;
		this.activityTimeSlotSeatAmount = activityTimeSlotSeatAmount;
	}

	// Getter & Setter
	public Integer getActivityTimeSlotID() {
		return activityTimeSlotID;
	}

	public void setActivityTimeSlotID(Integer activityTimeSlotID) {
		this.activityTimeSlotID = activityTimeSlotID;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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
