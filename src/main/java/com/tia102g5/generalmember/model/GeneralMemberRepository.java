// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/


package com.tia102g5.generalmember.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface GeneralMemberRepository extends JpaRepository<GeneralMember, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete GeneralMember where memberID =?1", nativeQuery = true)
	void deleteByMemberID(int memberID);
	
	
	GeneralMember findByMemberAccount(String memberAccount);
	
}
