package com.tia102g5.administrator.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{

	@Transactional
	@Modifying
	@Query(value = "delete Administrator where administratorID =?1", nativeQuery = true)
	void deleteByAdministratorID(int administratorID);
	
	Administrator findByAdministratorAccount (String administratorAccount);
}
