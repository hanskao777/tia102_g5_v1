package com.tia102g5.ticket.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.activity.model.ActivityService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	ActivityService activitySvc;
	
	/********************* 跳轉 **********************/
	//售票資訊
	@GetMapping("/ticketDisplay")
	public String ticketDisplay() {
		return "back-end-partner/ticket/ticketDisplay";
	}
	/********************* 跳轉 **********************/
	
	/********************* bean **********************/
	//查全部，給 ticketDisplay 用
		@ModelAttribute("activityListData")
		protected List<Activity> referenceListData(Model model) {
	    	List<Activity> list = activitySvc.getAll();
	    	return list;
		}
	/********************* bean **********************/
	
}
