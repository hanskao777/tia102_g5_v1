package com.tia102g5.article.model; 

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
@Table(name = "article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleID", updatable = false)
	private Integer articleID;

	@Column(name = "articleTitle")
	private String articleTitle;

	@Column(name = "memberID")
	private Integer memberID;

	@Column(name = "articleContent", columnDefinition = "TEXT")
	private String articleContent;

	@Column(name = "boardID")
	private Integer boardID;

	@Column(name = "articleStatus")
	private Integer articleStatus;

	@Column(name = "articleCreateTime", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date  articleCreateTime;
	
//	@PrePersist
//	protected void onCreate() {
//	    this.articleCreateTime = new Date();
//	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "memberID", referencedColumnName = "memberID")
//	private Generalmember generalmember;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "articleID", referencedColumnName = "articleID")
//	private Heart heart;
//
//	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
//	@OrderBy("boardID asc")
//	private Set<Article> articles;

	
	
	public Integer getArticleID() {
		return articleID;
	}

	public void setArticleID(Integer articleID) {
		this.articleID = articleID;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Integer getBoardID() {
		return boardID;
	}

	public void setBoardID(Integer boardID) {
		this.boardID = boardID;
	}

	public Integer getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(Integer articleStatus) {
		this.articleStatus = articleStatus;
	}

	public Date  getArticleCreateTime() {
		return articleCreateTime;
	}

//	public void setArticleCreateTime(Date  articleCreateTime) {
//		this.articleCreateTime = articleCreateTime;
//	}

//	public Generalmember getGeneralmember() {
//		return generalmember;
//	}
//
//	public void setGeneralmember(Generalmember generalmember) {
//		this.generalmember = generalmember;
//	}
//
//	public Heart getHeart() {
//		return heart;
//	}
//
//	public void setHeart(Heart heart) {
//		this.heart = heart;
//	}
//
//	public Set<Article> getArticles() {
//		return articles;
//	}
//
//	public void setArticles(Set<Article> articles) {
//		this.articles = articles;
//	}

//	@Override
//	public String toString() {
//		return "Article [articleID=" + articleID + ", articleTitle=" + articleTitle + ", memberID=" + memberID + ", articleContent=" + articleContent + ", boardID=" + boardID
//				+ ", articleStatus=" + articleStatus +  "]";
//	}



}
