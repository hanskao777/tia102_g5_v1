package com.tia102g5.venuerental.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tia102g5.venue.model.Venue;
import com.tia102g5.venuetimeslot.model.VenueTimeSlot;

@Entity
@Table(name = "VenueRental")
public class VenueRental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int venueRentalID;

	@ManyToOne
	@JoinColumn(name = "venueID", nullable = false)
	private Venue venue;

	@Column(nullable = false)
	private int partnerID;

	@Column(nullable = false, length = 255)
	private String activityName;

	@Column(nullable = false, length = 255)
	private String proposal;

	@Column(nullable = false)
	private int venueRentalStatus;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date venueRentalStartDate;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date venueRentalEndDate;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date venueRentalCreateTime;

	@Column(nullable = false, length = 255)
	private String venueRentalCode;

	@OneToMany(mappedBy = "venueRental")
	private Set<VenueTimeSlot> venueTimeSlots;

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

	public int getPartnerID() {
		return partnerID;
	}

	public void setPartnerID(int partnerID) {
		this.partnerID = partnerID;
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

	public VenueRental() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VenueRental(int venueRentalID, Venue venue, int partnerID, String activityName, String proposal,
			int venueRentalStatus, Date venueRentalStartDate, Date venueRentalEndDate, Date venueRentalCreateTime,
			String venueRentalCode, Set<VenueTimeSlot> venueTimeSlots) {
		super();
		this.venueRentalID = venueRentalID;
		this.venue = venue;
		this.partnerID = partnerID;
		this.activityName = activityName;
		this.proposal = proposal;
		this.venueRentalStatus = venueRentalStatus;
		this.venueRentalStartDate = venueRentalStartDate;
		this.venueRentalEndDate = venueRentalEndDate;
		this.venueRentalCreateTime = venueRentalCreateTime;
		this.venueRentalCode = venueRentalCode;
		this.venueTimeSlots = venueTimeSlots;
	}

	@Override
	public String toString() {
		return "VenueRental [venueRentalID=" + venueRentalID + ", venue=" + venue + ", partnerID=" + partnerID
				+ ", activityName=" + activityName + ", proposal=" + proposal + ", venueRentalStatus="
				+ venueRentalStatus + ", venueRentalStartDate=" + venueRentalStartDate + ", venueRentalEndDate="
				+ venueRentalEndDate + ", venueRentalCreateTime=" + venueRentalCreateTime + ", venueRentalCode="
				+ venueRentalCode + ", venueTimeSlots=" + venueTimeSlots + "]";
	}

}
