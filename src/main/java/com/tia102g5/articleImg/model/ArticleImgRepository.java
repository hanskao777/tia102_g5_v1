// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g5.articleImg.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tia102g5.article.model.Article;

public interface ArticleImgRepository extends JpaRepository<ArticleImg, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from articleImg where articleImgID =?1", nativeQuery = true)
	void deleteByaArticleImgID(int articleImgID);

	//● (自訂)條件查詢
	@Query(value = "from ArticleImg where articleImgID=?1 and article like ?2 and articlePic like ?3 and articleImgCreateTime like ?4 order by articleImgID")
	List<ArticleImg> findByOthers(Integer articleImgID, Article article, byte[] articlePic, Date articleImgCreateTime);
}