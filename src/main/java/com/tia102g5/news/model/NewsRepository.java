package com.tia102g5.news.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface NewsRepository extends JpaRepository<News, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete from News where newsID =?1", nativeQuery = true)
	void deleteByNewsID(int newsID);
}
