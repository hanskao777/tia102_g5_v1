// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

package com.tia102g5.prosecute.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tia102g5.article.model.Article;
import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.message.model.Message;

public interface ProsecuteRepository extends JpaRepository<Prosecute, Integer> {

	@Transactional
	@Modifying
	@Query(value = "delete from prosecute where prosecuteID =?1", nativeQuery = true)
	void deleteByProsecuteID(int prosecuteID);

	//● (自訂)條件查詢
	@Query(value = "from Prosecute where prosecuteID=?1 and memberID=?2 and articleID=?3 and prosecuteReason like ?4 and messageID=?5 and prosecuteStatus=?6 and prosecuteCreateTime=?7 order by prosecuteID")
	List<Prosecute> findByCustomConditions(
	    Integer prosecuteID, GeneralMember memberID, Article articleID, String prosecuteReason,  Message messageID, Integer prosecuteStatus, Date prosecuteCreateTime);
}