package com.tia102g5.orderitem.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete from OrderItem where orderItemID =?1", nativeQuery = true)
	void deleteByOrderItemID(int orderItemID);
}
