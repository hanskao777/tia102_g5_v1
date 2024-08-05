package com.tia102g5.commodity.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommodityRepository extends JpaRepository<Commodity, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete from Commodity where commodityID =?1", nativeQuery = true)
	void deleteByCommodityID(int commodityID);
}
