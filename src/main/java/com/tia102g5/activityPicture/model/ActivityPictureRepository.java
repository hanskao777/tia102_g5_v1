package com.tia102g5.activityPicture.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityPictureRepository extends JpaRepository<ActivityPicture, Integer>{
		
		//刪除
		@Transactional
		@Modifying
		@Query(value = "DELETE FROM activityPicture WHERE activityPictureID = ?1", nativeQuery = true)
		void deleteByActivityPictureID(Integer activityPictureID);
		
}
