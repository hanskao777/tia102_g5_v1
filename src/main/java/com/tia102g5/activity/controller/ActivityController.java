package com.tia102g5.activity.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.activity.model.ActivityService;
import com.tia102g5.activityPicture.model.ActivityPicture;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	ActivityService activitySvc;
	
/********************* 跳轉 **********************/	
//////////////// 前台 ////////////////
	//活動資訊總攬
	@GetMapping("/activityInfoAll")
	public String activityInfo() {
		return "/front-end/activity/activityInfoAll";
	}
	
	//活動資訊
	@GetMapping("/activityInfoOne")
	public String activityInfoOne(@RequestParam("activityID") String activityID, ModelMap model) {
		Activity activity = activitySvc.getOneActivity(Integer.valueOf(activityID));
		model.addAttribute("activity", activity);
		
		return "/front-end/activity/activityInfoOne";
	}
//////////////// 前台 ////////////////
	
//////////////// 後台 ////////////////
	//活動資訊
	@GetMapping("/activityDisplay")
	public String activityDisplay() {
		return "back-end-partner/activity/activityDisplay";
	}
	
	//活動資訊設定
	@PostMapping("/activityConfig")
	public String activityConfig(@RequestParam("activityID") String activityID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		Activity activity = activitySvc.getOneActivity(Integer.valueOf(activityID));
		
		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("activity", activity);
		
		return "back-end-partner/activity/activityConfig";
	}
//////////////// 後台 ////////////////
/********************* 跳轉 **********************/
	
/********************* action *******************/
	
//	@GetMapping("addActivity")
//	public String addActivity(ModelMap model) {
//		
//	}
	
	//activityConfig 送出更新
	@PostMapping("update")
	public String update(@Valid Activity activity, BindingResult result, ModelMap model,
			@RequestParam("activityPictures") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(activity, result, "activityPictures");

		//////// 設置未在表單中的資訊 ////////////
		
		Activity activityORI = activitySvc.getOneActivity(activity.getActivityID());
		
		activity.setPartnerMember(activityORI.getPartnerMember());
		activity.setVenue(activityORI.getVenue());
		activity.setVenueRental(activityORI.getVenueRental());
		activity.setActivityStatus(1);
		activity.setTicketSetStatus(activityORI.getTicketSetStatus());
		
		////////設置未在表單中的資訊 ////////////
		
		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
				Set<ActivityPicture> activityPictures = activityORI.getActivityPictures();
				
				activity.setActivityPictures(activityPictures);
		} else {
			for (MultipartFile multipartFile : parts) {
				ActivityPicture activityPicture = new ActivityPicture();
				
				activityPicture.setActivityPicture(multipartFile.getBytes());
				activityPicture.setActivity(activity);
				activity.getActivityPictures().add(activityPicture);
			}
		}
		if (result.hasErrors()) {
			return "back-end-partner/activity/activityConfig";
		}
		/*************************** 2.開始修改資料 *****************************************/
		
		
		activitySvc.updateActivity(activity);
		
		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		activity = activitySvc.getOneActivity(Integer.valueOf(activity.getActivityID()));
		model.addAttribute("activity", activity);
		
		return "back-end-partner/activity/activityDisplay"; // 修改成功後轉交activityDispaly.html
	}
/********************* action ********************/
	
/********************* bean **********************/
	//查全部，給 activityDisplay 用
	@ModelAttribute("activityListData")
	protected List<Activity> referenceListData(Model model) {
    	List<Activity> list = activitySvc.getAll();
    	
    	return list;
	}
/********************* bean **********************/

/*************** ExceptionHandler ****************/
	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(Activity activity, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(activity, "activity");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		
		return result;
	}
/*************** ExceptionHandler ****************/
		
}
