package com.tia102g5.commodity.model;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.partnermember.model.PartnerMember;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete from Commodity where commodityID =?1", nativeQuery = true)
	void deleteByCommodityID(int commodityID);

	List<Commodity> findByActivity_ActivityID(Integer activityID);
//	List<Activity> findByPartnerMember_PartnerID(Integer partnerID);

	@Query("SELECT DISTINCT c.activity FROM Commodity c WHERE c.partnerMember.partnerID = :partnerID")
	List<Activity> findActivitiesByPartnerMemberID(@Param("partnerID") Integer partnerID);

	// 添加這個方法來直接從Activity表查詢
	@Query("SELECT a FROM Activity a WHERE a.partnerMember.partnerID = :partnerID")
	List<Activity> findAllActivitiesByPartnerMemberID(@Param("partnerID") Integer partnerID);

}

//	@Query("SELECT DISTINCT c.activity FROM Commodity c WHERE c.partnerMember.partnerID = :partnerID")
//	List<Activity> findActivitiesByPartnerMemberID(@Param("partnerID") Integer partnerID);}

//	List<Activity> findActivitiesByPartnerMemberID(Integer partnerID);
//	List<Commodity> findByPartnerMember_PartnerMemberID(Integer partnerID);


