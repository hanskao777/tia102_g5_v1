package com.tia102g5.activityareaprice.model;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityAreaPriceRepository extends JpaRepository<ActivityAreaPrice, Integer> {
	
//	因為被ticket fk所以沒用，要去fk設定delete on cascade
	@Transactional
	@Modifying
	@Query(value = "delete from activityareaprice where activityareaprice = ?1",nativeQuery = true)
	void deleteByPrice(BigDecimal activityAreaPrice);
	
}
