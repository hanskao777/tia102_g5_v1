// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g5.article.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from article where articleID =?1", nativeQuery = true)
	void deleteByArticleID(int articleID);

	//● (自訂)條件查詢
	@Query(value = "from Article where articleID=?1 and articleCategory like ?2 and articleTitle like ?3 and articleContent like ?4 and articleStatus=?5 and board.boardID=?6 order by articleID")
	List<Article> findByOthers(int articleID, String articleCategory, String articleTitle, String articleContent, Integer articleStatus, Integer boardID);
}