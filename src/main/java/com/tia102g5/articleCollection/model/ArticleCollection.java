package com.tia102g5.articleCollection.model; 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "articlecollection") //文章收藏
public class ArticleCollection implements java.io.Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleCollectionID", updatable = false) //文章收藏ID
	private Integer articleCollectionID;
	
	
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID") //會員ID
	private GeneralMember generalMember; 
	


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articleID", referencedColumnName = "articleID") //文章ID
	private Article article; 
	


	@Column(name = "collectionCreateTime", updatable = false, insertable = false) //收藏時間
	@Temporal(TemporalType.TIMESTAMP)
	private Date  collectionCreateTime;
		
	
	public ArticleCollection() { 
	}
	

	public Integer getArticleCollectionID() {
		return articleCollectionID;
	}
	
	
	public void setArticleCollectionID(Integer articleCollectionID) {
		this.articleCollectionID = articleCollectionID;
	}
	
	
	public Date getCollectionCreateTime() {
		return collectionCreateTime;
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
//	    return "ArticleCollection [articleCollectionID=" + articleCollectionID 
//				+ ", generalMember=" + generalMember	+ ", article=" + article
//	           + ", collectionCreateTime=" + collectionCreateTime + "]";
//	}


	



	

}
