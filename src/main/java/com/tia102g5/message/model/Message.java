package com.tia102g5.message.model; 

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
import com.tia102g5.prosecute.model.Prosecute;


@Entity
@Table(name = "message")
public class Message {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "messageID", updatable = false)
	private Integer messageID;
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
//	private GeneralMember generalMember; 


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleID", referencedColumnName = "articleID")
	private Article article; 
	
	
	@Column(name = "messageContent")
	private String messageContent ;	
	
	@Column(name = "messageStatus ")
	private Integer messageStatus ;
	

	@Column(name = "messageCreateTime", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date  messageCreateTime;

	
	

	@OneToOne(mappedBy = "message", fetch = FetchType.LAZY)
	private Prosecute prosecute; 
	
	

	public Integer getMessageID() {
		return messageID;
	}


	public void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}


	public String getMessageContent() {
		return messageContent;
	}


	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}


	public Integer getMessageStatus() {
		return messageStatus;
	}


	public void setMessageStatus(Integer messageStatus) {
		this.messageStatus = messageStatus;
	}


	public Date getMessageCreateTime() {
		return messageCreateTime;
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

	


	public Prosecute getProsecute() {
		return this.prosecute;
	}


	public void setProsecute(Prosecute prosecute) {
		this.prosecute = prosecute;
	}


	
//	@Override
//	public String toString() {
//	    return "Message [messageID=" + messageID 
//	           + ", generalMember=" + generalMember
//	           + ", article=" + (article != null ? article.getArticleID() : "null")
//	           + ", messageContent=" + messageContent 
//	           + ", messageStatus=" + messageStatus 
//	           + ", messageCreateTime=" + messageCreateTime 
//	           + ", prosecute=" + (prosecute != null ? prosecute.getProsecuteID() : "null") + "]";
//	}




}