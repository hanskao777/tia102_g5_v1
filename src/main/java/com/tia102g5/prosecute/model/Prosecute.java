package com.tia102g5.prosecute.model; 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tia102g5.article.model.Article;
import com.tia102g5.message.model.Message;


@Entity
@Table(name = "prosecute")
public class Prosecute {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prosecuteID", updatable = false)
	private Integer prosecuteID;
	

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
//	private GeneralMember generalMember; 
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleID", referencedColumnName = "articleID")
	private Article article; 
	
	
	
	@Column(name = "prosecuteReason")
	private String prosecuteReason ;	
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "messageID", referencedColumnName = "messageID")
	private Message message; 

	
	@Column(name = "prosecuteStatus")
	private Integer prosecuteStatus ;
		

	@Column(name = "prosecuteCreateTime", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date   prosecuteCreateTime;

	

	public Prosecute() {
		
	}


	public Integer getProsecuteID() {
		return prosecuteID;
	}


	public void setProsecuteID(Integer prosecuteID) {
		this.prosecuteID = prosecuteID;
	}




	public String getProsecuteReason() {
		return prosecuteReason;
	}


	public void setProsecuteReason(String prosecuteReason) {
		this.prosecuteReason = prosecuteReason;
	}




	public Integer getProsecuteStatus() {
		return prosecuteStatus;
	}


	public void setProsecuteStatus(Integer prosecuteStatus) {
		this.prosecuteStatus = prosecuteStatus;
	}


	public Date getProsecuteCreateTime() {
		return prosecuteCreateTime;
	}

	

//	public GeneralMember getGeneralMember() {
//		return this.generalMember;
//	}
//
//
//	public void setGeneralMember(GeneralMember generalMember) {
//		this.generalMember = generalMember;
//	}


	public Article getArticle() {
		return this.article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public Message getMessage() {
		return this.message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}


//	@Override
//	public String toString() {
//	    return "Prosecute [prosecuteID=" + prosecuteID 
//	           + ", generalMember=" + generalMember
//	           + ", article=" + (article != null ? article.getArticleID() : "null")
//	           + ", prosecuteReason=" + prosecuteReason
//	           + ", message=" + (message != null ? message.getMessageID() : "null")
//	           + ", prosecuteStatus=" + prosecuteStatus 
//	           + ", prosecuteCreateTime=" + prosecuteCreateTime + "]";
//	}



}