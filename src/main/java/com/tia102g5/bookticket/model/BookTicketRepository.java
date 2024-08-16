package com.tia102g5.bookticket.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookTicketRepository extends JpaRepository<BookTicket, Integer>{

	@Query("SELECT bt FROM BookTicket bt JOIN FETCH bt.activityTimeSlot WHERE bt.generalMember.memberID = :memberID")
	List<BookTicket> findByGeneralMember_MemberIDWithTimeSlot(@Param("memberID") Integer memberID);
	
	List<BookTicket> findByGeneralMember_MemberID(Integer memberID);
}
