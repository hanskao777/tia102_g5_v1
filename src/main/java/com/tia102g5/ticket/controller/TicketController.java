package com.tia102g5.ticket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@GetMapping("bookTicket")
	public String bookTicket(HttpSession session, ModelMap model) {
		
		return "front-end/ticket/bookTicket";
	}
//////////////// 前台 ////////////////
	
//////////////// 後台 ////////////////
	//售票資訊
	@GetMapping("ticketDisplay")
	public String ticketDisplay() {
		return "back-end-partner/ticket/ticketDisplay";
	}
//////////////// 後台 ////////////////
/********************* 跳轉 **********************/
	
/********************* action **********************/
	//刪減票券
	@PostMapping("deleteOneTicket")
	public String deleteOneTicket(@RequestParam("count") Integer count, HttpSession session, ModelMap model) {
		List<Ticket> ticketList = (List<Ticket>)session.getAttribute("ticketList");
		ticketList.remove(count - 1);
		
		//若移除至 0 票券，重導至 seatSelect.html
		if(ticketList.isEmpty()) {
			return "redirect:/seatSelect";
		}
		
		session.setAttribute("ticketList", ticketList);
		
		return "redirect:/ticket/bookTicket";
	}
/********************* action **********************/
	
/********************* bean **********************/
	//查全部，給 ticketDisplay 用
	@ModelAttribute("activityListData")
	protected List<Activity> referenceActivityListData(Model model) {
    	List<Activity> list = activitySvc.getAll();
    	
    	return list;
	}
	
/********************* bean **********************/
	
}
