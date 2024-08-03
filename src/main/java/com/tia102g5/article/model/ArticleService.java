package com.tia102g5.article.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g5.articleImg.model.ArticleImg;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Article3;


@Service("articleService")
public class ArticleService {

	@Autowired
	ArticleRepository repository;
	
	@Autowired
    private SessionFactory sessionFactory;

	public void addArticle(Article article) {
		repository.save(article);
	}

	public void updateArticle(Article article) {
		repository.save(article);
	}

	public void deleteArticle(Integer articleID) {
		if (repository.existsById(articleID))
			repository.deleteByArticleID(articleID);
//		    repository.deleteById(articleID);
	}

	public Article getOneArticle(Integer articleID) {
		Optional<Article> optional = repository.findById(articleID);
//		return optional.get();
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<Article> getAll() {
		return repository.findAll();
	}

	public List<Article> getAll(Map<String, String[]> map) {
		return HibernateUtil_CompositeQuery_Article3.getAllC(map,sessionFactory.openSession());
	}
	
	
    public List<String> getAllCategories() {
        return repository.findAllDistinctCategories();
    }
	
	public Set<ArticleImg> getArticleImgsByArticleID(Integer articleID) {
		return getOneArticle(articleID).getArticleImgs();
	}

}
