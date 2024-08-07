package com.tia102g5.venuerental.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

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
	@JoinColumn(name = "venueID", referencedColumnName = "venueID")
	private Venue venue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "partnerID", referencedColumnName = "partnerID")
	private PartnerMember partnerMember;

	@Column(name = "activityName", length = 255)
	private String activityName;

	@Lob
	@Column(name = "proposal")
	private byte[] proposal;

	@Column(name = "venueRentalStatus")
	private int venueRentalStatus;

	@Column(name = "venueRentalStartDate")
	private Date venueRentalStartDate;

	@Column(name = "venueRentalEndDate")
	private Date venueRentalEndDate;

	@Column(name = "venueRentalCreateTime", updatable = false, insertable = false)
	private Timestamp venueRentalCreateTime;

	@Column(name = "venueRentalCode", length = 255)
	private String venueRentalCode;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "venueRental")
	@OrderBy("venueTimeSlotID asc")
	private Set<VenueTimeSlot> venueTimeSlots;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "venueRental")
	private Activity activity;

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

	public byte[] getProposal() {
		return proposal;
	}

	public void setProposal(byte[] proposal) {
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

	public Timestamp getVenueRentalCreateTime() {
		return venueRentalCreateTime;
	}

	public void setVenueRentalCreateTime(Timestamp venueRentalCreateTime) {
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

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
