package com.tia102g5.orders.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete from Orders where ordersID =?1", nativeQuery = true)
	void deleteByOrdersID(int ordersID);
}
