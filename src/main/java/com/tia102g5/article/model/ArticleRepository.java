// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g5.article.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tia102g5.board.model.Board;
import com.tia102g5.generalmember.model.GeneralMember;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from article where articleID =?1", nativeQuery = true)
	void deleteByArticleID(int articleID);

	//● (自訂)條件查詢
	@Query(value = "FROM Article WHERE articleID=?1 AND articleCategory LIKE ?2 AND articleTitle LIKE ?3 AND memberID = ?4 AND articleContent LIKE ?5 AND boardID = ?6 AND articleStatus = ?7 AND articleCreateTime = ?8 ORDER BY articleID")
	List<Article> findByOthers(Integer articleID, String articleCategory, String articleTitle, GeneralMember memberID, String articleContent, Board boardID, Integer articleStatus, Date articleCreateTime);

	 // 獲取所有不同的 articleCategory
    @Query("SELECT DISTINCT a.articleCategory FROM Article a WHERE a.articleCategory IS NOT NULL")
    List<String> findAllDistinctCategories();

}