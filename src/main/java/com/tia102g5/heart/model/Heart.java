package com.tia102g5.heart.model; 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tia102g5.article.model.Article;
import com.tia102g5.generalmember.model.GeneralMember;


@Entity
@Table(name = "heart")
public class Heart implements java.io.Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "heartID", updatable = false)
	private Integer heartID;
	
	

	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
	private GeneralMember generalMember; 

	

	@ManyToOne
	@JoinColumn(name = "articleID", referencedColumnName = "articleID")
	private Article article; 
	
	

	@Column(name = "heartCreateTime", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date  heartCreateTime;
	
	
	public Heart() { 
	}
	

	public Integer getHeartID() {
		return heartID;
	}

	public void setHeartID(Integer heartID) {
		this.heartID = heartID;
	}



	public Date getHeartCreateTime() {
		return heartCreateTime;
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

	
	
//	@Override
//	public String toString() {
//	    return "Heart [heartID=" + heartID 
//	           + ", generalMember=" + generalMember	 + ", article=" + article 
//	           + ", heartCreateTime=" + heartCreateTime + "]";
//	}

	



}