package com.tia102g5.activity.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM activity WHERE activityID = ?1", nativeQuery = true)
	void deleteByActivityID(Integer activityID);

}
