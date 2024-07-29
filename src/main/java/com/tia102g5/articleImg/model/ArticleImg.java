package com.tia102g5.articleImg.model; 

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tia102g5.article.model.Article;


@Entity
@Table(name = "articleImg")
public class ArticleImg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleImgID", updatable = false)
	private Integer articleImgID;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleID", referencedColumnName = "articleID")
	private Article article; 


	
	@Column(name = "articlePic", columnDefinition = "mediumblob")
	private byte[] articlePic;

	@Column(name = "articleImgCreateTime", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date  articleImgCreateTime;
	
	@PrePersist
	protected void onCreate() {
	    this.articleImgCreateTime = new Date();
	}
		
	
	public Integer getArticleImgID() {
		return articleImgID;
	}

	public void setArticleImgID(Integer articleImgID) {
		this.articleImgID = articleImgID;
	}




	public byte[] getArticlePic() {
		return articlePic;
	}

	public void setArticlePic(byte[] articlePic) {
		this.articlePic = articlePic;
	}


	public Date getArticleImgCreateTime() {
		return articleImgCreateTime;
	}


	public Article getArticle() {
		return this.article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	@Override
	public String toString() {
		return "ArticleImg [articleImgID=" + articleImgID + ", article=" + article + ", articlePic="
				+ Arrays.toString(articlePic) + ", articleImgCreateTime=" + articleImgCreateTime + "]";
	}


	

}
