package com.tia102g5.generalmember.model;



import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "GeneralMember")
public class GeneralMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memberID", updatable = false) // 會員ID
	private Integer memberID;

	@Column(name = "memberAccount") // "帳號(E-mail)"
	private String memberAccount;

	@Column(name = "memberPassword") // "密碼"
	private String memberPassword;

	@Column(name = "memberName") // "姓名"
	private String memberName;

	@Column(name = "memberPhone") // "電話"
	private String memberPhone;

	@Column(name = "memberNickName") // "暱稱"
	private String memberNickName;

	@Column(name = "memberAddress") // "地址"
	private String memberAddress;

	@Column(name = "nationalID") // "身分證字號"
	private String nationalID;

	@Column(name = "gender") // "性別"
	private String gender;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday") // "生日"
	private Date birthday;

	@Column(name = "memberPicture", columnDefinition = "mediumblob") // "大頭照"
	private byte[] memberPicture;

	@Column(name = "memberStatus") // "帳號狀態 0:帳號已黑單 1:帳號正常"
	private Integer memberStatus;

	@Column(name = "memberCreateTime", updatable = false) // "帳號建立時間"
	private Date memberCreateTime;

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getNationalID() {
		return nationalID;
	}

	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public byte[] getMemberPicture() {
		return memberPicture;
	}

	public void setMemberPicture(byte[] memberPicture) {
		this.memberPicture = memberPicture;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Date getMemberCreateTime() {
		return memberCreateTime;
	}


	public void setMemberCreateTime(Date memberCreateTime) {
		this.memberCreateTime = memberCreateTime;
	}

	@Override
	public String toString() {
		return "GeneralMember [memberID=" + memberID + ", memberAccount=" + memberAccount + ", memberPassword="
				+ memberPassword + ", memberName=" + memberName + ", memberPhone=" + memberPhone + ", memberNickName="
				+ memberNickName + ", memberAddress=" + memberAddress + ", nationalID=" + nationalID + ", gender="
				+ gender + ", birthday=" + birthday + ", memberPicture=" + Arrays.toString(memberPicture)
				+ ", memberStatus=" + memberStatus + ", memberCreateTime=" + memberCreateTime + "]";
	}
	
	
}
