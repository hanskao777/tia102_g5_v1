package com.tia102g5.articleImg.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("articleImgService")
public class ArticleImgService {

	@Autowired
	ArticleImgRepository repository;

	public void addArticleImg(ArticleImg articleimg) {
		repository.save(articleimg);
	}

	public void updateDept(ArticleImg articleimg) {
		repository.save(articleimg);
	}

	public void deleteArticleImg(Integer articleImgID) {
		if (repository.existsById(articleImgID))
			repository.deleteById(articleImgID);
	}


	public ArticleImg getOneArticleImg(Integer articleImgID) {
		Optional<ArticleImg> optional = repository.findById(articleImgID);
//		return optional.get();
		return optional.orElse(null);  // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<ArticleImg> getAll() {
		return repository.findAll();
	}



	
}
