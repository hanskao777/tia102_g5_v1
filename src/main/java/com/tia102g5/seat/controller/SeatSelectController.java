package com.tia102g5.seat.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g5.activityareaprice.model.ActivityAreaPrice;
import com.tia102g5.activityareaprice.model.ActivityAreaPriceService;
import com.tia102g5.seat.model.SeatService;
import com.tia102g5.seatstatus.model.SeatStatusService;
import com.tia102g5.ticket.model.TicketService;
import com.tia102g5.venuearea.model.VenueAreaService;

@Controller
@RequestMapping("/seatSelect")
public class SeatSelectController {

	@Autowired
	private TicketService ticketService;

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
	public ResponseEntity<?> submitSeats(@RequestParam String seat1, @RequestParam(required = false) String seat2,
	                                     @RequestParam(required = false) String seat3, @RequestParam(required = false) String seat4,
	                                     @RequestParam(required = false) String seat5) {
	    List<String> seatNames = Arrays.asList(seat1, seat2, seat3, seat4, seat5).stream().filter(Objects::nonNull)
	            .filter(seat -> !seat.trim().isEmpty()).collect(Collectors.toList());

	    List<Integer> seatStatusIds = new ArrayList<>();
	    Integer venueId = 1; // 場館ID寫死為1
	    Integer activityTimeSlotId = 1; // 活動時段ID寫死為1
	    Integer activityId = 1;//活動ID寫死為1

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
	                return ResponseEntity.badRequest().body("未找到區域: " + venueAreaName);
	            }

	            System.out.println("找到區域ID: " + venueAreaId + " 對應區域名稱: " + venueAreaName);

	            // 使用 ActivityAreaPriceService 查找 activityAreaPriceId
	            Integer activityAreaPriceId = activityAreaPriceService.findActivityAreaPriceID(venueAreaId, activityId);
	            if (activityAreaPriceId == null) {
	                System.out.println("未找到活動區域價格: 區域ID " + venueAreaId + ", 活動ID " + activityId);
	                return ResponseEntity.badRequest().body("未找到活動區域價格: " + venueAreaName);
	            }

	            System.out.println("找到活動區域價格ID: " + activityAreaPriceId + " 對應區域ID: " + venueAreaId + ", 活動ID: " + activityId);

	            // 使用 venueId 和 seatNumber 找出 seatId
	            Integer seatId = seatService.findSeatId(venueId, seatName);
	            if (seatId != null) {
	                System.out.println("找到座位ID: " + seatId + " 對應座位名稱: " + seatName + " 在區域ID: " + venueAreaId);
	                Integer seatStatusId = seatStatusService
	                        .getSeatStatusByActivityTimeSlotIdAndSeatId(activityTimeSlotId, seatId);
	                if (seatStatusId != null) {
	                    System.out.println("找到座位狀態ID: " + seatStatusId + " 對應座位ID: " + seatId + " 在區域ID: " + venueAreaId);
	                    seatStatusIds.add(seatStatusId);
	                } else {
	                    System.out.println("未找到座位狀態: " + seatName + " 在區域ID: " + venueAreaId);
	                    return ResponseEntity.badRequest().body("未找到座位狀態: " + seatName);
	                }
	            } else {
	                System.out.println("座位不存在: " + seatName + " 在區域ID: " + venueAreaId);
	                return ResponseEntity.badRequest().body("座位不存在: " + seatName);
	            }
	        }

	        System.out.println("所有座位狀態ID: " + seatStatusIds);
	        return ResponseEntity.ok(seatStatusIds);
	    } catch (Exception e) {
	        System.out.println("處理座位選擇時發生錯誤: " + e.getMessage());
	        e.printStackTrace();
	        return ResponseEntity.internalServerError().body("處理座位選擇時發生錯誤: " + e.getMessage());
	    }
	}
}
