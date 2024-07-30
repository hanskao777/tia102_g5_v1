package com.tia102g5.partnermember.model;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PartnerMemberRepository extends JpaRepository<PartnerMember, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete PartnerMember where partnerID =?1", nativeQuery = true)
	void deleteBypartnerID(int partnerID);
}
