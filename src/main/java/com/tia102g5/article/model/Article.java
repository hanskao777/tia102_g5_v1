package com.tia102g5.article.model; 

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.tia102g5.articleCollection.model.ArticleCollection;
import com.tia102g5.articleImg.model.ArticleImg;
import com.tia102g5.board.model.Board;
import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.heart.model.Heart;
import com.tia102g5.message.model.Message;
import com.tia102g5.prosecute.model.Prosecute;



@Entity
@Table(name = "article") //文章
public class Article implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleID", updatable = false) //文章ID
	private Integer articleID;
	
	
	@Column(name = "articleCategory") //文章類別
	private String articleCategory;


	
	@Column(name = "articleTitle") //文章標題
	@NotEmpty(message="請填寫文章標題")
	@Size(min=5,max=50,message="文章標題:長度必需在{min}到{max}之間")
	private String articleTitle;


	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID") //會員ID
	private GeneralMember generalMember; 

	
	@Column(name = "articleContent", columnDefinition = "TEXT") //文章內容
	@NotEmpty(message="文章內容請勿空白")
	private String articleContent;

	
	@ManyToOne
	@JoinColumn(name = "boardID", referencedColumnName = "boardID") //各版ID
	private Board board; 
		

	@Column(name = "articleStatus")
	private Integer articleStatus = 1;
	

	@Column(name = "articleCreateTime", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date  articleCreateTime;
	

	

	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@OrderBy("heartID asc")
	private Set<Heart> hearts = new HashSet<Heart>();
	

	@OneToOne(mappedBy = "article", fetch = FetchType.LAZY)
	private Prosecute prosecute; 
	
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@OrderBy("articleCollectionID asc")
	private Set<ArticleCollection> articleCollections = new HashSet<ArticleCollection>();
	
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@OrderBy("messageID asc")
	private Set<Message> messages = new HashSet<Message>();
	
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@OrderBy("articleImgID asc")
	private Set<ArticleImg> articleImgs = new HashSet<ArticleImg>();
	
	
	
	public Article() { 
	}

	
	
	public Integer getArticleID() {
		return articleID;
	}

	public void setArticleID(Integer articleID) {
		this.articleID = articleID;
	}
	
	public String getArticleCategory() {
		return articleCategory;
	}

	
	public void setArticleCategory(String articleCategory) {
		this.articleCategory = articleCategory;
	}
	

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	
	
	
	
	public GeneralMember getGeneralMember() {
		return this.generalMember;
	}
	
	public void setGeneralMember(GeneralMember generalMember) {
		this.generalMember = generalMember;
	}
	

	
	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	

	public Board getBoard() {
		return this.board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
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


	

	public Set<Heart> getHearts() {
		return this.hearts;
	}

	public void setHearts(Set<Heart> hearts) {
		this.hearts = hearts;
	}

	
	public Prosecute getProsecute() {
		return this.prosecute;
	}

	public void setProsecute(Prosecute prosecute) {
		this.prosecute = prosecute;
	}
	

	public Set<ArticleCollection> getArticleCollections() {
		return this.articleCollections;
	}

	public void setArticleCollections(Set<ArticleCollection> articleCollections) {
		this.articleCollections = articleCollections;
	}

	public Set<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public Set<ArticleImg> getArticleImgs() {
		return this.articleImgs;
	}

	public void setArticleImgs(Set<ArticleImg> articleImgs) {
		this.articleImgs = articleImgs;
	}

	
	
//	@Override
//	public String toString() {
//		return "Article [articleID=" + articleID + ", articleCategory=" + articleCategory + ", articleTitle="
//				+ articleTitle + ", generalMember=" + generalMember + ", articleContent=" + articleContent + ", board=" + board + ", articleStatus="
//				+ articleStatus + ", articleCreateTime=" + articleCreateTime + ", hearts=" + hearts + ", prosecute="
//				+ prosecute + ", articleCollections=" + articleCollections + ", messages=" + messages + ", articleImgs="
//				+ articleImgs + "]";
//	}

	
	
	

}
