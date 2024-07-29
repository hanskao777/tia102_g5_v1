package com.tia102g5.activitycollection.model;

import java.io.Serializable;
import java.sql.Timestamp;

//活動收藏
public class ActivityCollectionVO implements Serializable{

	private Integer activityCollectionID; //活動收藏ID
	private Integer memberID; //會員ID
	private Integer activityID; //活動ID
	private Timestamp activityCollectionTime; //活動收藏時間
	
	//建構子
	public ActivityCollectionVO() {
		super();
	}

	public ActivityCollectionVO(Integer activityCollectionID, Integer memberID, Integer activityID,
			Timestamp activityCollectionTime) {
		super();
		this.activityCollectionID = activityCollectionID;
		this.memberID = memberID;
		this.activityID = activityID;
		this.activityCollectionTime = activityCollectionTime;
	}

	//Getter & Setter
	public Integer getActivityCollectionID() {
		return activityCollectionID;
	}

	public void setActivityCollectionID(Integer activityCollectionID) {
		this.activityCollectionID = activityCollectionID;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getActivityID() {
		return activityID;
	}

	public void setActivityID(Integer activityID) {
		this.activityID = activityID;
	}

	public Timestamp getActivityCollectionTime() {
		return activityCollectionTime;
	}

	public void setActivityCollectionTime(Timestamp activityCollectionTime) {
		this.activityCollectionTime = activityCollectionTime;
	}
	
	@Override
	// 顯示活動收藏資料
	public String toString() {
		String activityCollectionVOShowInfo = "\n活動收藏資料: \n"
				+ "活動收藏 ID: " + activityCollectionID + "\n" 
				+ "會員 ID: " + memberID + "\n"
				+ "活動 ID: " + activityID + "\n"
				+ "活動收藏時間: " + activityCollectionTime + "\n";
		
		return activityCollectionVOShowInfo;
	}
	
}
