package com.tia102g5.news.model;

import com.tia102g5.announcement.model.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface NewsRepository extends JpaRepository<News, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete from News where newsID =?1", nativeQuery = true)
	void deleteByNewsID(int newsID);
	// 添加按狀態查詢的方法
	Page<News> findByNewsStatus(Integer status, Pageable pageable);

	// 添加按創建時間範圍查詢的方法
	Page<News> findByNewsCreateTimeBetween(Date startDate, Date endDate, Pageable pageable);
}