// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g5.heart.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tia102g5.article.model.Article;
import com.tia102g5.board.model.Board;
import com.tia102g5.generalmember.model.GeneralMember;

public interface HeartRepository extends JpaRepository<Heart, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from heart where heartID =?1", nativeQuery = true)
	void deleteByHeartID(int heartID);

	//● (自訂)條件查詢
	@Query(value = "from Heart where heartID=?1 and memberID =?2 and articleID =?3 and heartCreateTime =?4 order by heartID")
	List<Heart> findByOthers(int heartID , GeneralMember memberID , Article articleID , Date heartCreateTime);
}