package com.tia102g5.cart.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "delete from Cart where cartID =?1", nativeQuery = true)
	void deleteByCartID(int cartID);

}
