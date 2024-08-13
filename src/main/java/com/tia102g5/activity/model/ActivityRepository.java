package com.tia102g5.activity.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	//刪除
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM activity WHERE activityID = ?1", nativeQuery = true)
	void deleteByActivityID(Integer activityID);
	
//	// 查詢 (複合)
//	@Query(value = "from activity where activityID=?1 and activityName like?2 and hiredate=?3 order by activityID")
//	List<Activity> findByOthers(int activityID , String activityName , java.sql.Date hiredate);

}
