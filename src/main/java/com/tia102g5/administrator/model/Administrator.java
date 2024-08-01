package com.tia102g5.administrator.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
public class Administrator {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "administratorID", updatable = false) // "管理員ID"
	private Integer administratorID;
	
	@Column(name = "administratorAccount") // "帳號"
	private String administratorAccount;
	
	@Column(name = "administratorPassword") // "密碼"
	private String administratorPassword;
	
	@Column(name = "administratorCreateTime", updatable = false) // "帳號建立時間"
	private Date administratorCreateTime;
	
	@Column(name = "administratorStatus") // "帳號狀態 0:帳號正常 1:帳號停用"
	private Integer administratorStatus;
	
	
//	@OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL)
//	@OrderBy(newsID asc)
//	private Set<News> news;
	
	
//	@OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL)
//	@OrderBy(announcementID asc)
//	private Set<Announcement> announcement;

	public Administrator() {
		super();
	}

	public Administrator(Integer administratorID, String administratorAccount, String administratorPassword,
			Date administratorCreateTime, Integer administratorStatus) {
		super();
		this.administratorID = administratorID;
		this.administratorAccount = administratorAccount;
		this.administratorPassword = administratorPassword;
		this.administratorCreateTime = administratorCreateTime;
		this.administratorStatus = administratorStatus;
	}

	public Integer getAdministratorID() {
		return administratorID;
	}

	public void setAdministratorID(Integer administratorID) {
		this.administratorID = administratorID;
	}

	public String getAdministratorAccount() {
		return administratorAccount;
	}

	public void setAdministratorAccount(String administratorAccount) {
		this.administratorAccount = administratorAccount;
	}

	public String getAdministratorPassword() {
		return administratorPassword;
	}

	public void setAdministratorPassword(String administratorPassword) {
		this.administratorPassword = administratorPassword;
	}

	public Date getAdministratorCreateTime() {
		return administratorCreateTime;
	}

	public void setAdministratorCreateTime(Date administratorCreateTime) {
		this.administratorCreateTime = administratorCreateTime;
	}

	public Integer getAdministratorStatus() {
		return administratorStatus;
	}

	public void setAdministratorStatus(Integer administratorStatus) {
		this.administratorStatus = administratorStatus;
	}

	@Override
	public String toString() {
		return "Administrator [administratorID=" + administratorID + ", administratorAccount=" + administratorAccount
				+ ", administratorPassword=" + administratorPassword + ", administratorCreateTime="
				+ administratorCreateTime + ", administratorStatus=" + administratorStatus + "]";
	}
	
	
}
