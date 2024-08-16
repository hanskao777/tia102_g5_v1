package com.tia102g5.seat.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.activity.model.ActivityService;
import com.tia102g5.activityareaprice.model.ActivityAreaPrice;
import com.tia102g5.activityareaprice.model.ActivityAreaPriceService;
import com.tia102g5.activitytimeslot.model.ActivityTimeSlot;
import com.tia102g5.activitytimeslot.model.ActivityTimeSlotService;
import com.tia102g5.seat.model.SeatService;
import com.tia102g5.seatstatus.model.SeatStatus;
import com.tia102g5.seatstatus.model.SeatStatusService;
import com.tia102g5.ticket.model.Ticket;
import com.tia102g5.venuearea.model.VenueAreaService;

@Controller
@RequestMapping("/seatSelect")
public class SeatSelectController {

	@Autowired
	private ActivityTimeSlotService activityTimeSlotService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private SeatService seatService;

	@Autowired
	private SeatStatusService seatStatusService;

	@Autowired
	private VenueAreaService venueAreaService;

	@Autowired
	private ActivityAreaPriceService activityAreaPriceService;

	@GetMapping
	public String getSeatSelect() {
//    	@RequestParam("activityTimeSlotId") Integer activityTimeSlotId, ModelMap model//到時候需要加入這參數
//        ActivityTimeSlot activityTimeSlot = activityTimeSlotService.findById(activityTimeSlotId);
//        if (activityTimeSlot == null) {
//            return "redirect:/error";
//        }
//        
//        model.addAttribute("activityTimeSlot", activityTimeSlot);
		// 這裡可以添加更多需要傳遞給視圖的數據

		return "/front-end/ticket/seatSelect";
	}

	@PostMapping("/submitSeats")
	public String submitSeats(@RequestParam Integer activityTimeSlotID,
	        @RequestParam String seat1, 
	        @RequestParam(required = false) String seat2,
	        @RequestParam(required = false) String seat3, 
	        @RequestParam(required = false) String seat4,
	        @RequestParam(required = false) String seat5, 
	        HttpSession session,
	        RedirectAttributes redirectAttributes) {
	    List<String> seatNames = Arrays.asList(seat1, seat2, seat3, seat4, seat5).stream().filter(Objects::nonNull)
	            .filter(seat -> !seat.trim().isEmpty()).collect(Collectors.toList());

	    List<Ticket> ticketList = new ArrayList<>();
	    ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(activityTimeSlotID);
	    Integer activityId = activityTimeSlot.getActivity().getActivityID();// 活動ID從活動時段ID抓取
	    Activity activity = activityService.getOneActivity(activityId);
	    activity.setActivityID(activityId);
	    Integer venueId = activity.getVenue().getVenueID();// 場館ID從活動ID抓取

	    try {
	        System.out.println("處理座位選擇開始");
	        for (String seatName : seatNames) {
	            System.out.println("正在處理座位: " + seatName);

	            // 解析座位名稱，分離出區域名稱和座位號碼
	            String venueAreaName = seatName.replaceAll("\\d+", "");

	            System.out.println("解析後的區域名稱: " + venueAreaName);

	            // 使用 VenueAreaService 找出 venueAreaId
	            Integer venueAreaId = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, venueAreaName);
	            if (venueAreaId == null) {
	                System.out.println("未找到區域: " + venueAreaName);
	                redirectAttributes.addFlashAttribute("error", "未找到區域: " + venueAreaName);
	                return "redirect:/error";
	            }

	            System.out.println("找到區域ID: " + venueAreaId + " 對應區域名稱: " + venueAreaName);

	            // 使用 ActivityAreaPriceService 查找 ActivityAreaPrice
	            ActivityAreaPrice activityAreaPrice = activityAreaPriceService.findActivityAreaPrice(venueAreaId,
	                    activityId);
	            if (activityAreaPrice == null) {
	                System.out.println("未找到活動區域價格: 區域ID " + venueAreaId + ", 活動ID " + activityId);
	                redirectAttributes.addFlashAttribute("error", "未找到活動區域價格: " + venueAreaName);
	                return "redirect:/error";
	            }

	            System.out.println("找到活動區域價格ID: " + activityAreaPrice.getActivityAreaPriceID() + " 對應區域ID: "
	                    + venueAreaId + ", 活動ID: " + activityId);

	            // 使用 venueId 和 seatNumber 找出 seatId
	            Integer seatId = seatService.findSeatId(venueId, seatName);
	            if (seatId != null) {
	                System.out.println("找到座位ID: " + seatId + " 對應座位名稱: " + seatName + " 在區域ID: " + venueAreaId);
	                // 使用 activityTimeSlotId 和 seatId 找出 SeatStatus
	                SeatStatus seatStatus = seatStatusService
	                        .getSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotID, seatId);
	                if (seatStatus != null) {
	                    System.out.println("找到座位狀態ID: " + seatStatus.getSeatStatusID() + " 對應座位ID: " + seatId
	                            + " 在區域ID: " + venueAreaId);

	                    // Create a new Ticket object and add it to the list
	                    Ticket ticket = new Ticket();
	                    ticket.setSeatStatus(seatStatus);
	                    ticket.setActivityAreaPrice(activityAreaPrice);
	                    ticket.setActivityTimeSlot(activityTimeSlot);
	                    ticketList.add(ticket);
	                } else {
	                    System.out.println("未找到座位狀態: " + seatName + " 在區域ID: " + venueAreaId);
	                    redirectAttributes.addFlashAttribute("error", "未找到座位狀態: " + seatName);
	                    return "redirect:/error";
	                }
	            } else {
	                System.out.println("座位不存在: " + seatName + " 在區域ID: " + venueAreaId);
	                redirectAttributes.addFlashAttribute("error", "座位不存在: " + seatName);
	                return "redirect:/error";
	            }
	        }

	        // Store the ticketList in the session
	        session.setAttribute("ticketList", ticketList);

	        System.out.println("所有票券資訊已存入session");
	        return "redirect:/ticket/bookTicket";
	    } catch (Exception e) {
	        System.out.println("處理座位選擇時發生錯誤: " + e.getMessage());
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("error", "處理座位選擇時發生錯誤: " + e.getMessage());
	        return "redirect:/error";
	    }
	}
}
