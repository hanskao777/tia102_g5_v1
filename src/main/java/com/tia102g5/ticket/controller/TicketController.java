package com.tia102g5.ticket.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.activity.model.ActivityService;
import com.tia102g5.activitytimeslot.model.ActivityTimeSlot;
import com.tia102g5.bookticket.model.BookTicket;
import com.tia102g5.bookticket.model.BookTicketService;
import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.generalmember.model.GeneralMemberService;
import com.tia102g5.partnermember.model.PartnerMember;
import com.tia102g5.partnermember.model.PartnerMemberService;
import com.tia102g5.ticket.model.Ticket;
import com.tia102g5.ticket.model.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	ActivityService activitySvc;
	
	@Autowired
	TicketService ticketSvc;
	
	@Autowired
	GeneralMemberService memberSvc;
	
	@Autowired
	BookTicketService bookTicketSvc;
	
	@Autowired
	PartnerMemberService partnerSvc;
	
/********************* 跳轉 **********************/
//////////////// 前台 ////////////////
	//票券結帳
	@GetMapping("bookTicket")
	public String bookTicket(HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至會員登入頁面
		if(session.getAttribute("memberID") == null) {
			return "redirect:/generalmember/login";
		}
		
		List<Ticket> ticketList = (List<Ticket>)session.getAttribute("ticketList");
		BigDecimal total = BigDecimal.ZERO;
		
		//選購票券總價
		for(Ticket ticket : ticketList) {
			total = total.add(ticket.getActivityAreaPrice().getActivityAreaPrice());
		}
		
		model.addAttribute("total", total);
		
		return "front-end/ticket/bookTicket";
	}
//////////////// 前台 ////////////////
	
//////////////// 後台 ////////////////
	//售票資訊
	@GetMapping("ticketDisplay")
	public String ticketDisplay(HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至廠商登入頁面
		if(session.getAttribute("partnerID") == null) {
			return "redirect:/partnermember/partnerLogin";
		}
		
		//取得 Partner
		Integer partnerID = (Integer)session.getAttribute("partnerID");
		PartnerMember partner = partnerSvc.getOnePartnerMember(partnerID);
		
		//取得廠商所有 Activity
		Set<Activity> activities = partner.getActivities();

		model.addAttribute("partnerActivityListData", activities);
		
		return "back-end-partner/ticket/ticketDisplay";
	}
//////////////// 後台 ////////////////
/********************* 跳轉 **********************/
	
/********************* action **********************/
	//刪減票券
	@PostMapping("deleteOneTicket")
	public String deleteOneTicket(@RequestParam("count") Integer count, HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至會員登入頁面
		if(session.getAttribute("memberID") == null) {
			return "redirect:/generalmember/login";
		}
		
		List<Ticket> ticketList = (List<Ticket>)session.getAttribute("ticketList");
		ticketList.remove(count - 1);
		
		//若移除至 0 票券，重導至 seatSelect.html
		if(ticketList.isEmpty()) {
			return "redirect:/seatSelect";
		}
		
		session.setAttribute("ticketList", ticketList);
		
		return "redirect:/ticket/bookTicket";
	}
	
	//取消與結帳
	@PostMapping("confirm")
	public String confirm(@RequestParam("action") String action, @RequestParam("memberID") String memberID, @RequestParam("ticketMemberIDs") String[] ticketMemberIDs,
			@RequestParam("totalPrice") String totalPrice, HttpSession session, ModelMap model) {
		//確認是否登入，未登入重導至會員登入頁面
		if(session.getAttribute("memberID") == null) {
			return "redirect:/generalmember/login";
		}
				
		//取消
		if("cancel".equals(action)) {
			return "redirect:/";
		}
		
		//取得訂單
		BookTicket bookTicket = new BookTicket();
		
		//取得選購票券
		List<Ticket> ticketList = (List<Ticket>)session.getAttribute("ticketList");
		
		//設定持有人給票券
		for(int i = 0; i < ticketMemberIDs.length; i++) {
			//取得第 i 個持有人
			GeneralMember ticketMember = memberSvc.getOneGeneralMember(Integer.valueOf(ticketMemberIDs[i]));
			//取得第 i 張票券
			Ticket ticket = ticketList.get(i);
			
			ticket.setGeneralMember(ticketMember);
			ticket.setBookTicket(bookTicket);
		}
		
		//將票券設置至訂單
		for(Ticket ticket : ticketList) {
			bookTicket.getTickets().add(ticket);
		}
		
		//設置訂單資料
		bookTicket.setGeneralMember(memberSvc.getOneGeneralMember(Integer.valueOf(memberID))); //未從 session 取帳號
		
		ActivityTimeSlot activityTimeSlot = ticketList.get(0).getActivityTimeSlot();
		bookTicket.setActivity(activityTimeSlot.getActivity());
		bookTicket.setActivityTimeSlot(activityTimeSlot);
		
		bookTicket.setTicketQuantity(ticketList.size());
		bookTicket.setTotalPrice(new BigDecimal(totalPrice));
		
		//成立訂單存入資料庫
		bookTicketSvc.addBookTicket(bookTicket);
		
		//session 移除選購票券
		session.removeAttribute("ticketList");
		
		//跳轉
		return "redirect:/generalmember/myTicketOrders";
	}
/********************* action **********************/
	
}
