// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g5.articleCollection.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleCollectionRepository extends JpaRepository<ArticleCollection, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from articleCollection where articleCollectionID =?1", nativeQuery = true)
	void deleteByArticleCollectionID(int articleCollectionID);

	//● (自訂)條件查詢
		@Query(value = "from ArticleCollection where articleCollectionID=?1 and articleID like ?2 and collectionCreateTime like ?3 order by articleCollectionID")
		List<ArticleCollection> findByOthers(int articleCollectionID, int articleID, java.util.Date collectionCreateTime);
	}