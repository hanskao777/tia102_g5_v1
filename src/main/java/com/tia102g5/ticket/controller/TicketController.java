package com.tia102g5.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.activity.model.ActivityService;
import com.tia102g5.ticket.model.Ticket;
import com.tia102g5.ticket.model.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	ActivityService activitySvc;
	
	@Autowired
	TicketService ticketSvc;
	
/********************* 跳轉 **********************/
//////////////// 前台 ////////////////
	//票券結帳
	@GetMapping("/bookTicket")
	public String bookTicket() {
		return "front-end/ticket/bookTicket";
	}
//////////////// 前台 ////////////////
	
//////////////// 後台 ////////////////
	//售票資訊
	@GetMapping("/ticketDisplay")
	public String ticketDisplay() {
		return "back-end-partner/ticket/ticketDisplay";
	}
//////////////// 後台 ////////////////
/********************* 跳轉 **********************/
	
/********************* 跳轉 **********************/
	
/********************* 跳轉 **********************/
	
/********************* bean **********************/
	//查全部，給 ticketDisplay 用
	@ModelAttribute("activityListData")
	protected List<Activity> referenceActivityListData(Model model) {
    	List<Activity> list = activitySvc.getAll();
    	
    	return list;
	}
	
	//查欲結帳票券，給 bookTicket 用
	@ModelAttribute("ticketListData")
	protected List<Ticket> referenceTicketListData(Model model) {
		List<Ticket> list = ticketSvc.getAll();
		
		return list;
	}
/********************* bean **********************/
	
}
