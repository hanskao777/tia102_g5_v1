package com.tia102g5.commodity.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tia102g5.commodity.model.Commodity;
import com.tia102g5.commodity.model.CommodityService;
import com.tia102g5.commoditypicture.model.CommodityPicture;
import com.tia102g5.commoditypicture.model.CommodityPictureService;
import com.tia102g5.partnermember.model.PartnerMember;
import com.tia102g5.partnermember.model.PartnerMemberService;
import com.tia102g5.activity.model.Activity;
import com.tia102g5.activity.model.ActivityService;
import com.tia102g5.announcement.model.Announcement;

@Controller
@RequestMapping("/commodity")
@Validated
public class CommodityController {

//    調整視圖名稱以匹配項目結構。
//    根據需要添加或修改特定的業務邏輯。
//    調整錯誤處理和驗證邏輯以適應需求。
//    可能需要添加更多的方法來處理特定的查詢或操作。
	@Autowired
	CommodityService commoditySvc;

	@Autowired
	CommodityPictureService commodityPictureSvc;

	@Autowired
	PartnerMemberService partnerMemberSvc;

	@Autowired
	ActivityService activitySvc;

//	// 首頁商城活動頁面的Mapping
//	@GetMapping("/mall_activity")
//	public String mallActivity(Model model) {
//		return "/front-end/mall/mallActivity";
//	}
//	
	 @GetMapping("/mall_activity")
	    public String mallActivity(Model model, @RequestParam(defaultValue = "1") int page) {
		 
		 
		 List<Activity> activities = commoditySvc.getAllActivities();
	        model.addAttribute("activities", activities);
	        System.out.println("Activities size: " + activities.size());
//	        
//	        List<Activity> activity = commoditySvc.getAllActivitiesPicture();
//	        model.addAttribute("activity", activity);
//	        System.out.println("Activity size: " + activity.size());


		 
//	        int pageSize = 6; // 每頁顯示的活動數量
//	        Page<Activity> activityPage = commoditySvc.getAllActivitiesPaginated(PageRequest.of(page - 1, pageSize));
//	        model.addAttribute("activities", activityPage.getContent());
//	        model.addAttribute("currentPage", page);
//	        model.addAttribute("totalPages", activityPage.getTotalPages());		 
		 
//	        int pageSize = 6; // 每頁顯示的活動數量
//	        Page<Activity> activityPage = commoditySvc.getActivity(pageSize, PageRequest.of(page - 1, pageSize));

//	        model.addAttribute("activities", activityPage.getContent());
//	        model.addAttribute("currentPage", page);
//	        model.addAttribute("totalPages", activityPage.getTotalPages());
//	        List<Activity> activityList = commoditySvc.getAllactivity();
//	        model.addAttribute("activityList", activityList);
//	        System.out.println("Activities size: " + activityList.size());

	        

	 

	        return "front-end/mall/mallActivity";
	    }

	// 首頁商城活動商品頁面的Mapping
	@GetMapping("/mall_listActivityCommodities")
	public String listActivityCommodities(/* @RequestParam(required = false) Integer activityID, */ Model model,
			@RequestParam(defaultValue = "1") int page) {
//		List<Commodity> commodities = commoditySvc.getCommoditiesByActivity(activityID);
//		model.addAttribute("commodities", commodities);
		
		int pageSize = 9; // 每頁顯示的商品數量
		Integer activityID = 1; // 固定的 activityID

		Page<Commodity> commodityPage = commoditySvc.getCommoditiesByActivityPaginated(activityID,
				PageRequest.of(page - 1, pageSize));

		model.addAttribute("commodityList", commodityPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", commodityPage.getTotalPages());
		model.addAttribute("activityID", activityID);
		
		return "front-end/mall/listActivityCommodities";
	}
	
	

	


	// 首頁商城商品頁面的Mapping
	@GetMapping("/mall_listOneCommodity")
	public String listOneCommodity(@RequestParam("commodityID") String commodityID, ModelMap model) {
		Commodity commodity = commoditySvc.getOneCommodity(Integer.valueOf(commodityID));
		model.addAttribute("commodity", commodity);
		return "front-end/mall/listOneCommodity";
	}
	
	
	
	
	
	
	
	

	// 後台商城活動頁面,根據 partnerID 顯示已申請的活動
//    @GetMapping("/activityCommodityList")
//    public String getActivityByPartnerID(ModelMap model) {
//        Integer partnerID = 1;
//        List<Activity> list = commoditySvc.getActivitiesByPartnerMember(partnerID);
//        model.addAttribute("list", list);
////        List<Activity> list = activitySvc.findByPartnerMemberPartnerID(partnerID);
////        model.addAttribute("activityListData", list);
//        return "/back-end-partner/commodity/activityCommodity";
//    }
	@GetMapping("/activityCommodityList")
	public String getActivityByPartnerID(ModelMap model) {
		Integer partnerID = 1; // 這裡應該是從session或其他地方獲取當前登錄的partnermember ID
		List<Activity> activities = commoditySvc.getActivitiesByPartnerMember(partnerID);
//        List<Commodity> activities = commoditySvc.getActivityByPartner(partnerID);
//        List<Activity> activityList = activitySvc.getAll();
		model.addAttribute("activities", activities);

		// 添加這行用於調試
		System.out.println("Activities size: " + activities.size());
		return "/back-end-partner/commodity/activityCommodity";
	}

	// 後台商城商品頁面,根據 activityID 顯示該活動的商品
//    要用這個 @GetMapping("/listAllCommodity/{activityID}")
//    public String listAllCommodity(@PathVariable Integer activityID, ModelMap model) {
//        List<Commodity> commodities = commoditySvc.getCommoditiesByActivity(activityID);
//        model.addAttribute("commodityList", commodities);
//        model.addAttribute("activityID", activityID);  // 添加這行以便在視圖中使用
//        return "/back-end-partner/commodity/commodity";
//    }
	@GetMapping("/listAllCommodity")
	public String listAllCommodity(/* @RequestParam(required = false) Integer activityID, */ ModelMap model,
			@RequestParam(defaultValue = "1") int page) {
//        Integer activityID = 1;
//        List<Commodity> commodities = commoditySvc.getCommoditiesByActivity(activityID);
//        model.addAttribute("commodityList", commodities);
//        
//        int pageSize = 5; // 每頁顯示的商品數量
//        Page<Commodity> commodityPage = commoditySvc.getAllPaginated(PageRequest.of(page - 1, pageSize));
//
//        model.addAttribute("commodityList", commodityPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", commodityPage.getTotalPages());

		int pageSize = 5; // 每頁顯示的商品數量
		Integer activityID = 1; // 固定的 activityID

		Page<Commodity> commodityPage = commoditySvc.getCommoditiesByActivityPaginated(activityID,
				PageRequest.of(page - 1, pageSize));

		model.addAttribute("commodityList", commodityPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", commodityPage.getTotalPages());
		model.addAttribute("activityID", activityID);

//        if (activityID == null) {
//            // 如果沒有提供 activityID，可以顯示所有商品或者重定向到活動選擇頁面
//            List<Commodity> allCommodities = commoditySvc.getAll();
//            model.addAttribute("commodityList", allCommodities);
//        } else {
//            // 根據 activityID 獲取該活動的商品
//            List<Commodity> commodities = commoditySvc.getCommoditiesByActivity(activityID);
//            model.addAttribute("commodityList", commodities);
//        }

//        List<Commodity> commodities;
//        if (activityID == null) {
//            // 如果沒有提供 activityID，顯示所有商品
//            commodities = commoditySvc.getAll();
//        } else {
//            // 根據 activityID 獲取該活動的商品
//            commodities = commoditySvc.getCommoditiesByActivity(activityID);
//        }
//        model.addAttribute("commodityList", commodities);
//
//        // 獲取所有活動列表，用於可能的下拉選擇
//        List<Activity> activities = activitySvc.getAll();
//        model.addAttribute("activityList", activities);

		return "/back-end-partner/commodity/commodity";
	}

//    @Controller
//    public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
//
//        @RequestMapping("/error")
//        public String handleError(HttpServletRequest request, Model model) {
//            // 獲取錯誤信息
//            Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//            if (status != null) {
//                int statusCode = Integer.parseInt(status.toString());
//                model.addAttribute("statusCode", statusCode);
//            }
//            return "error";  // 創建一個 error.html 視圖
//        }
//
//        public String getErrorPath() {
//            return "/error";
//        }
//    }

	@ModelAttribute("commodityListData")
	protected List<Commodity> referenceListData(Model model) {
		List<Commodity> list = commoditySvc.getAll();
		return list;
	}

//    @GetMapping("addCommodity")
//    public String addCommodity(@RequestParam("activityID") Integer activityID,
//                               @RequestParam("partnerID") Integer partnerID,
//                               ModelMap model) {
//        Commodity commodity = new Commodity();
//        Activity activity = activitySvc.getOneActivity(activityID);
//        PartnerMember partnerMember = partnerMemberSvc.getOnePartnerMember(partnerID);
//
//        commodity.setActivity(activity);
//        commodity.setPartnerMember(partnerMember);
//
//        model.addAttribute("commodity", commodity);
//        model.addAttribute("activityID", activityID);
//        model.addAttribute("partnerID", partnerID);
//
//        return "back-end-partner/commodity/addCommodity";
//    }

	// 新增商品
//  要加這個   @GetMapping("addCommodity/{activityID}")
//    public String addCommodity(@PathVariable Integer activityID, ModelMap model) {
//        Commodity commodity = new Commodity();
//        Activity activity = activitySvc.getOneActivity(activityID);
//        commodity.setActivity(activity);
//        model.addAttribute("commodity", commodity);
//
//        List<PartnerMember> partnerMemberList = partnerMemberSvc.getAll();
//        model.addAttribute("partnerMemberListData", partnerMemberList);
//
//        return "back-end-partner/commodity/addCommodity";
//    }
	@GetMapping("addCommodity")
	public String addCommodity(ModelMap model) {
		Commodity commodity = new Commodity();
		model.addAttribute("commodity", commodity);

		List<PartnerMember> partnerMemberList = partnerMemberSvc.getAll();
		model.addAttribute("partnerMemberListData", partnerMemberList);

		List<Activity> activityList = activitySvc.getAll();
		model.addAttribute("activityListData", activityList);
		model.addAttribute("activityList", activityList);

		return "back-end-partner/commodity/addCommodity";
	}

	@PostMapping("insert")
	public String insert(@Valid Commodity commodity, BindingResult result, ModelMap model,
			@RequestParam(value = "commodityPic", required = false) MultipartFile[] parts) throws IOException {
		if (result.hasErrors()) {
			return "back-end-partner/commodity/addCommodity";
		}

		// 先保存 Commodity
		commoditySvc.addCommodity(commodity);

		// 處理圖片上傳邏輯
		if (parts != null && parts.length > 0) {
			for (MultipartFile part : parts) {
				if (!part.isEmpty()) {
					CommodityPicture commodityPicture = new CommodityPicture();
					commodityPicture.setCommodity(commodity);
					commodityPicture.setCommodityPicture(part.getBytes());
					commodityPictureSvc.addCommodityPicture(commodityPicture);
				}
			}
		}

//        commoditySvc.addCommodity(commodity, commodity.getActivity().getActivityID(), commodity.getPartnerMember().getPartnerID());

//        commoditySvc.addCommodity(commodity);

		List<Commodity> list = commoditySvc.getAll();
		model.addAttribute("commodityListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/commodity/listAllCommodity";
	}

	// 修改
	@PostMapping("updateCommodity")
	public String updateCommodity(@RequestParam("commodityID") String commodityIDStr, ModelMap model) {
		Integer commodityID = Integer.valueOf(commodityIDStr);
		Commodity commodity = commoditySvc.getOneCommodity(commodityID);

		List<PartnerMember> partnerMemberList = partnerMemberSvc.getAll();
		model.addAttribute("partnerMemberListData", partnerMemberList);

		List<Activity> activityList = activitySvc.getAll();
		model.addAttribute("activityListData", activityList);

		model.addAttribute("commodity", commodity);
		model.addAttribute("activityList", activityList);

		return "back-end-partner/commodity/updateCommodity";
	}

	@PostMapping("update")
	public String update(@Valid Commodity commodity, BindingResult result, ModelMap model,
			@RequestParam(value = "commodityPic", required = false) MultipartFile[] parts) throws IOException {
		if (result.hasErrors()) {
			return "back-end-partner/commodity/updateCommodity";
		}

		if (result.hasErrors()) {
			List<Activity> activityList = activitySvc.getAll();
			model.addAttribute("activityList", activityList);
			return "back-end-partner/commodity/updateCommodity";
		}
		// 確保 activity 不為 null
		if (commodity.getActivity() == null || commodity.getActivity().getActivityID() == null) {
			// 如果沒有選擇活動,可以設置一個默認值或返回錯誤
			model.addAttribute("error", "請選擇一個活動");
			return "back-end-partner/commodity/updateCommodity";
		}
		// 處理圖片更新邏輯
		if (parts != null && parts.length > 0) {
			for (MultipartFile part : parts) {
				if (!part.isEmpty()) {
					CommodityPicture commodityPicture = new CommodityPicture();
					commodityPicture.setCommodity(commodity);
					commodityPicture.setCommodityPicture(part.getBytes());
					commodityPictureSvc.addCommodityPicture(commodityPicture);
				}
			}
		}

		commoditySvc.updateCommodity(commodity);

		model.addAttribute("success", "- (修改成功)");
		commodity = commoditySvc.getOneCommodity(commodity.getCommodityID());
		model.addAttribute("commodity", commodity);
		return "redirect:/commodity/listAllCommodity";
	}

	@PostMapping("delete")
	public String delete(@RequestParam("commodityID") String commodityID, ModelMap model) {
		commoditySvc.deleteCommodity(Integer.valueOf(commodityID));
		List<Commodity> list = commoditySvc.getAll();
		model.addAttribute("commodityListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end-partner/commodity/commodity";
	}

//    複合查詢
//    @PostMapping("listCommodities_ByCompositeQuery")
//    public String listAllCommodity(HttpServletRequest req, Model model) {
//        Map<String, String[]> map = req.getParameterMap();
//        List<Commodity> list = commoditySvc.getAll(map);
//        model.addAttribute("commodityListData", list);
//        return "front-end/commodity/listAllCommodity";
//    }

	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
			@NotEmpty(message = "商品編號: 請勿空白") @Digits(integer = 4, fraction = 0, message = "商品編號: 請填數字-請勿超過{integer}位數") @Min(value = 1, message = "商品編號: 不能小於{value}") @Max(value = 1000, message = "商品編號: 不能超過{value}") @RequestParam("commodityID") String commodityID,
			ModelMap model) {

		Commodity commodity = commoditySvc.getOneCommodity(Integer.valueOf(commodityID));

		if (commodity == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "back-end-partner/commodity/commodity";
		}

		model.addAttribute("commodity", commodity);
		model.addAttribute("getOne_For_Display", "true");

		return "back-end-partner/commodity/commodity";
	}

//	@GetMapping("/list")
//	public String listCommodities(@RequestParam(defaultValue = "1") int page, Model model) {
//		int pageSize = 10; // 每頁顯示的商品數量
//		Page<Commodity> commodityPage = commoditySvc.getAllPaginated(PageRequest.of(page - 1, pageSize));
//
//		model.addAttribute("commodities", commodityPage.getContent());
//		model.addAttribute("currentPage", page);
//		model.addAttribute("totalPages", commodityPage.getTotalPages());
//
//		return "back-end-partner/commodity/commodity";
//	}

//	@PostMapping("/setPostTime")
//	public String setPostTime(
//			@RequestParam("postTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime postTime) {
//		// 實現設置上架時間的邏輯
//		return "redirect:/commodity/list";
//	}

//    @GetMapping("/activityList")
//    public String listActivities(@RequestParam(defaultValue = "1") int page, Model model) {
//        int pageSize = 10; // 每頁顯示的活動數量
//        Page<Activity> activityPage = activitySvc.getAllPaginated(PageRequest.of(page - 1, pageSize));
//
//        model.addAttribute("activities", activityPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", activityPage.getTotalPages());
//
//        return "front-end/commodity/activityCommodity";
//    }

	@GetMapping("/listByActivity/{activityID}")
	public String listCommoditiesByActivity(@PathVariable Integer activityID, Model model) {
		List<Commodity> commodities = commoditySvc.getCommoditiesByActivity(activityID);
		model.addAttribute("commodities", commodities);
		return "back-end-partner/commodity/commodityByActivity"; // 需要創建這個新的視圖
//        return "back-end-partner/commodity/commodity";

	}
//    ActivityService getCommoditiesByActivity()

	@ExceptionHandler(value = { ConstraintViolationException.class })
	public ModelAndView handleError(HttpServletRequest req, ConstraintViolationException e, Model model) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		StringBuilder strBuilder = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			strBuilder.append(violation.getMessage() + "<br>");
		}

		List<Commodity> list = commoditySvc.getAll();
		model.addAttribute("commodityListData", list);

		String message = strBuilder.toString();
		return new ModelAndView("back-end-partner/commodity/select_page", "errorMessage", "請修正以下錯誤:<br>" + message);
	}
}