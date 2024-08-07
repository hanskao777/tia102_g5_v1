package com.tia102g5.activity.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.activity.model.ActivityService;
import com.tia102g5.partnermember.model.PartnerMemberService;

@Controller
@Validated
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	ActivityService activitySvc;
	
	@Autowired
	PartnerMemberService partnerSvc;
	
//	@Autowired
//	VenueService venueSvc;
	
//	@Autowired
//	VenueRetalService venueRentalSvc;
	
	//查詢 (單一)
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
			/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			@RequestParam("activityID") String activityID, ModelMap model) {
		/***************************2.開始查詢資料*********************************************/
		Activity activity = activitySvc.getOneActivity(Integer.valueOf(activityID));
		
		List<Activity> activityList = activitySvc.getAll();
		model.addAttribute("empListData", activityList);
		
		if(activity == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "back-end/activity/select_page";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("activity", activity);
		model.addAttribute("getOne_For_Display", "true");
		
		return "back-end/activity/select_page";
	}
	
//	@GetMapping("addActivity")
//	public String addActivity(ModelMap model) {
//		
//	}
	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ModelAndView handlerError(HttpServletRequest req, ConstraintViolationException e, Model model) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		StringBuilder strBuilder = new StringBuilder();
		for(ConstraintViolation<?> violation : violations) {
			strBuilder.append(violation.getMessage() + "<br>");
		}
		
		List<Activity> activityList = activitySvc.getAll();
		model.addAttribute("activityListData", activityList);
		
		String message = strBuilder.toString();
		
		return new ModelAndView("back-end/activity/select_page", "errorMessage", "請修正以下錯誤:<br>" + message);
	}
	
}
