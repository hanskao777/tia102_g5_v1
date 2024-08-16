package com.tia102g5.bookticket.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookTicketService {

	@Autowired
    private BookTicketRepository bookTicketRepository;
	
	public List<BookTicket> getTicketOrdersByMemberId(Integer memberID) {
	    return bookTicketRepository.findByGeneralMember_MemberIDWithTimeSlot(memberID);
	}
}
