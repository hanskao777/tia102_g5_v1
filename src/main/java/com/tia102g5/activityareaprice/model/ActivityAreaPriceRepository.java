package com.tia102g5.activityareaprice.model;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityAreaPriceRepository extends JpaRepository<ActivityAreaPrice, Integer> {
	
//	因為被ticket fk所以沒用Q，看來是所有delete都不行
//	@Transactional
//	@Modifying
//	@Query(value = "delete from activityareaprice where activityareaprice = ?1",nativeQuery = true)
//	void deleteByPrice(BigDecimal activityAreaPrice);
	
}
