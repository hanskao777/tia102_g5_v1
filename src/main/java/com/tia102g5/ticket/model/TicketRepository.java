package com.tia102g5.ticket.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
		
		//刪除
		@Transactional
		@Modifying
		@Query(value = "DELETE FROM ticket WHERE ticketID = ?1", nativeQuery = true)
		void deleteByTicketID(Integer ticketID);
		
}
