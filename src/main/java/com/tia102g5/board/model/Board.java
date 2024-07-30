package com.tia102g5.board.model; 

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.tia102g5.article.model.Article;


@Entity
@Table(name = "board")
public class Board {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "boardID", updatable = false)
	private Integer boardID;
	
	@Column(name = "boardName")
	private String boardName;
	
	

	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@OrderBy("articleID asc")
	private Set<Article> articles = new HashSet<Article>();
	
	
	
	public Board() { 
	}
	
	
	public Integer getBoardID() {
		return boardID;
	}

	public void setBoardID(Integer boardID) {
		this.boardID = boardID;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	

	public Set<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	

	@Override
	public String toString() {
	    return "Board [boardID=" + boardID + ", boardName=" + boardName + ", articlesCount=" + articles.size() + "]";
	}
	
	


}