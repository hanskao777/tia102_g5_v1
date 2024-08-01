package com.tia102g5.venuerental.model;

import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.partnermember.model.PartnerMember;
import com.tia102g5.venue.model.Venue;
import com.tia102g5.venuetimeslot.model.VenueTimeSlot;

@Entity
@Table(name = "venuerental")
public class VenueRental implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venueRentalID")
	private int venueRentalID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venueID", referencedColumnName = "venueID", nullable = false)
	private Venue venue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "partnerID", referencedColumnName = "partnerID", nullable = false)
	private PartnerMember partnerMember;

	@Column(name = "activityName", nullable = false, length = 255)
	private String activityName;

	@Column(name = "proposal", nullable = false, length = 255)
	private String proposal;

	@Column(name = "venueRentalStatus", nullable = false)
	private int venueRentalStatus;

	@Column(name = "venueRentalStartDate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date venueRentalStartDate;

	@Column(name = "venueRentalEndDate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date venueRentalEndDate;

	@Column(name = "venueRentalCreateTime", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date venueRentalCreateTime;

	@Column(name = "venueRentalCode", nullable = false, length = 255)
	private String venueRentalCode;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venueRental")
	@OrderBy("venueTimeSlotID asc")
	private Set<VenueTimeSlot> venueTimeSlots;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venueRental")
	@OrderBy("activityID asc")
	private Set<Activity> activities;

	public VenueRental() {
	}

	public int getVenueRentalID() {
		return venueRentalID;
	}

	public void setVenueRentalID(int venueRentalID) {
		this.venueRentalID = venueRentalID;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public PartnerMember getPartnerMember() {
		return partnerMember;
	}

	public void setPartnerMember(PartnerMember partnerMember) {
		this.partnerMember = partnerMember;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public int getVenueRentalStatus() {
		return venueRentalStatus;
	}

	public void setVenueRentalStatus(int venueRentalStatus) {
		this.venueRentalStatus = venueRentalStatus;
	}

	public Date getVenueRentalStartDate() {
		return venueRentalStartDate;
	}

	public void setVenueRentalStartDate(Date venueRentalStartDate) {
		this.venueRentalStartDate = venueRentalStartDate;
	}

	public Date getVenueRentalEndDate() {
		return venueRentalEndDate;
	}

	public void setVenueRentalEndDate(Date venueRentalEndDate) {
		this.venueRentalEndDate = venueRentalEndDate;
	}

	public Date getVenueRentalCreateTime() {
		return venueRentalCreateTime;
	}

	public void setVenueRentalCreateTime(Date venueRentalCreateTime) {
		this.venueRentalCreateTime = venueRentalCreateTime;
	}

	public String getVenueRentalCode() {
		return venueRentalCode;
	}

	public void setVenueRentalCode(String venueRentalCode) {
		this.venueRentalCode = venueRentalCode;
	}

	public Set<VenueTimeSlot> getVenueTimeSlots() {
		return venueTimeSlots;
	}

	public void setVenueTimeSlots(Set<VenueTimeSlot> venueTimeSlots) {
		this.venueTimeSlots = venueTimeSlots;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

}
