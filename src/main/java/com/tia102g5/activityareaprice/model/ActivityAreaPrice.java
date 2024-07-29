package com.tia102g5.activityareaprice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tia102g5.venuearea.model.VenueArea;

@Entity
@Table(name = "ActivityAreaPrice")
public class ActivityAreaPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int activityAreaPriceID;

	@ManyToOne
	@JoinColumn(name = "venueAreaID", nullable = false)
	private VenueArea venueArea;

	@Column(nullable = false)
	private int activityID;

	@Column(nullable = false)
	private int activityAreaPrice;

	public int getActivityAreaPriceID() {
		return activityAreaPriceID;
	}

	public void setActivityAreaPriceID(int activityAreaPriceID) {
		this.activityAreaPriceID = activityAreaPriceID;
	}

	public VenueArea getVenueArea() {
		return venueArea;
	}

	public void setVenueArea(VenueArea venueArea) {
		this.venueArea = venueArea;
	}

	public int getActivityID() {
		return activityID;
	}

	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}

	public int getActivityAreaPrice() {
		return activityAreaPrice;
	}

	public void setActivityAreaPrice(int activityAreaPrice) {
		this.activityAreaPrice = activityAreaPrice;
	}

	public ActivityAreaPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityAreaPrice(int activityAreaPriceID, VenueArea venueArea, int activityID, int activityAreaPrice) {
		super();
		this.activityAreaPriceID = activityAreaPriceID;
		this.venueArea = venueArea;
		this.activityID = activityID;
		this.activityAreaPrice = activityAreaPrice;
	}

}