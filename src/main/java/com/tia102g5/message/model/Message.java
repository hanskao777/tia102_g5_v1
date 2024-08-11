package com.tia102g5.message.model; 

import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tia102g5.article.model.Article;
import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.prosecute.model.Prosecute;


@Entity
@Table(name = "message") //留言
public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "messageID", updatable = false) //留言ID
	private Integer messageID;
	
	
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID") //會員ID
	private GeneralMember generalMember; 


	@JsonBackReference //解決循環引用問題,不序列化父端
	@ManyToOne
	@JoinColumn(name = "articleID", referencedColumnName = "articleID") //文章ID
	private Article article; 
	
	
	@Column(name = "messageContent") //留言內容
	@NotEmpty(message="請輸入留言內容")
	@Size(min=2,max=500,message="留言內容必需在{min}字到{max}字之間")
	private String messageContent ;	
	
	
	@Column(name = "messageStatus ") //留言狀態
	private Integer messageStatus = 1 ;
	

	@Column(name = "messageCreateTime", updatable = false, insertable = false) //留言時間
	@Temporal(TemporalType.TIMESTAMP)
	private Date  messageCreateTime;

	
	

	@OneToOne(mappedBy = "message",cascade = CascadeType.ALL, fetch = FetchType.LAZY) //檢舉
	private Prosecute prosecute; 
	
	
	public Message() { 
	}
	

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



	public GeneralMember getGeneralMember() {
		return this.generalMember;
	}


	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}


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