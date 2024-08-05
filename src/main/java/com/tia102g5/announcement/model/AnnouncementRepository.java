package com.tia102g5.announcement.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface AnnouncementRepository extends JpaRepository<Announcement, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "delete from Announcement where announcementID =?1", nativeQuery = true)
	void deleteByAnnouncementID(int announcementID);
}
