package com.tia102g5.seat.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tia102g5.activity.model.Activity;
import com.tia102g5.activity.model.ActivityService;
import com.tia102g5.activityareaprice.model.ActivityAreaPrice;
import com.tia102g5.activityareaprice.model.ActivityAreaPriceService;
import com.tia102g5.activitytimeslot.model.ActivityTimeSlot;
import com.tia102g5.activitytimeslot.model.ActivityTimeSlotService;
import com.tia102g5.seat.model.PriceForm;
import com.tia102g5.seat.model.SeatPriceData;
import com.tia102g5.seat.model.SeatService;
import com.tia102g5.seatstatus.model.SeatStatusService;
import com.tia102g5.venuearea.model.VenueAreaService;

@Controller
@RequestMapping("/seatReservationAndPricing")
public class SeatReservationAndPricingController {

	private static final Logger logger = LoggerFactory.getLogger(SeatReservationAndPricingController.class);

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

	@PostMapping
	public String getSeatReservationAndPricing(@RequestParam Integer activityID,
			@RequestParam Integer activityTimeSlotID, Model model) {
		logger.info("Accessing seat reservation and pricing page for Activity ID: {} and Time Slot ID: {}", activityID,
				activityTimeSlotID);

		// 獲取 VIP、A、B 區的價格
		BigDecimal vipPrice = getPriceForArea("VIP", activityID);
		BigDecimal aPrice = getPriceForArea("A", activityID);
		BigDecimal bPrice = getPriceForArea("B", activityID);

		// 創建一個 PriceForm 對象並設置初始值
		PriceForm priceForm = new PriceForm();
		priceForm.setVipPrice(vipPrice);
		priceForm.setAPrice(aPrice);
		priceForm.setBPrice(bPrice);

		// 將 PriceForm 添加到模型中
		model.addAttribute("priceForm", priceForm);
		model.addAttribute("activityID", activityID);
		model.addAttribute("activityTimeSlotID", activityTimeSlotID);

		return "/back-end-partner/ticket/seatReservationAndPricing";
	}

	@PostMapping("/setSeatsAndPrices")
	@ResponseBody
	public ResponseEntity<?> setSeatsAndPrices(@RequestBody SeatPriceData data, @RequestParam Integer activityID,
			@RequestParam Integer activityTimeSlotID) {
		logger.info("Received request to set seats and prices for Activity ID: {} and Time Slot ID: {}", activityID,
				activityTimeSlotID);

		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(activityTimeSlotID);
		Activity activity = activityService.getOneActivity(activityID);
		Integer venueId = activity.getVenue().getVenueID();

		Map<String, Object> response = new HashMap<>();

		try {
			logger.info("Processing data...");

			// 驗證價格
			if (!validatePrices(data.getPrices())) {
				logger.warn("Invalid prices received: {}", data.getPrices());
				response.put("error", "價格不能小於500");
				return ResponseEntity.badRequest().body(response);
			}

			// 處理保留座位
			if (data.getReservedSeats() != null && !data.getReservedSeats().isEmpty()) {
				logger.info("Reserved seats:");
				for (String seatName : data.getReservedSeats()) {
					logger.info(" - Seat Name: {}", seatName);
					Integer seatId = seatService.findSeatId(venueId, seatName);
					if (seatId != null) {
						seatStatusService.updateSeatStatusToReserved(seatId, activityTimeSlotID);
						logger.info("   Updated seat status for Seat ID: {} to Reserved (3)", seatId);

						String venueAreaName = getVenueAreaNameFromSeatName(seatName);
						if (venueAreaName != null) {
							Integer venueAreaId = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId,
									venueAreaName);
							if (venueAreaId != null) {
								logger.info("   Seat belongs to Venue Area ID: {}", venueAreaId);
							} else {
								logger.warn("   No corresponding Venue Area ID found for Venue Area Name: {}",
										venueAreaName);
							}
						} else {
							logger.warn("   Unable to determine Venue Area Name for Seat Name: {}", seatName);
						}
					} else {
						logger.warn("   No corresponding Seat ID found for Seat Name: {}", seatName);
					}
				}
				response.put("reservedSeats", data.getReservedSeats());
			} else {
				logger.info("No reserved seats");
				response.put("reservedSeats", "None");
			}

			// 處理票價
			if (data.getPrices() != null && !data.getPrices().isEmpty()) {
				logger.info("Ticket prices:");
				for (Map.Entry<String, Integer> entry : data.getPrices().entrySet()) {
					String venueAreaName = entry.getKey();
					Integer price = entry.getValue();
					logger.info(" - {} area: ${}", venueAreaName, price);

					Integer venueAreaId = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId,
							venueAreaName);
					if (venueAreaId != null) {
						try {
							ActivityAreaPrice updatedPrice = activityAreaPriceService
									.updateOrCreateActivityAreaPrice(venueAreaId, activityID, new BigDecimal(price));
							logger.info(
									"   Updated/Created price for Venue Area ID: {}, Activity ID: {}, New Price: ${}",
									venueAreaId, activityID, updatedPrice.getActivityAreaPrice());
						} catch (Exception e) {
							logger.error("Error updating/creating price for Venue Area ID: {}, Activity ID: {}",
									venueAreaId, activityID, e);
						}
					} else {
						logger.warn("   No corresponding Venue Area ID found for Venue Area Name: {}", venueAreaName);
					}
				}
				response.put("prices", data.getPrices());
			} else {
				logger.info("No ticket prices set");
				response.put("prices", "Not set");
			}

			logger.info("Data processing completed successfully");
			response.put("message", "設定已成功保存");
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			logger.error("Error occurred while processing the request", e);
			response.put("error", "處理請求時發生錯誤: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PostMapping("/seatStatus")
	@ResponseBody
	public ResponseEntity<?> getSeatStatus(@RequestParam Integer activityTimeSlotID) {
		logger.info("Fetching seat status for Time Slot ID: {}", activityTimeSlotID);

		try {
			List<String> soldSeats = seatStatusService.getSeatNamesWithStatus2(activityTimeSlotID);
			List<String> reservedSeats = seatStatusService.getSeatNamesWithStatus3(activityTimeSlotID);

			Map<String, Object> response = new HashMap<>();
			response.put("soldSeats", soldSeats);
			response.put("reservedSeats", reservedSeats);

			logger.info("Successfully fetched seat status");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Error occurred while fetching seat status", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("獲取座位狀態時發生錯誤");
		}
	}

	@PostMapping("/cancelReservation")
	@ResponseBody
	public ResponseEntity<?> cancelReservation(@RequestBody Map<String, String> request) {
		logger.info("Received request to cancel seat reservation");

		String seatName = request.get("seatName");
		Integer activityTimeSlotID = Integer.parseInt(request.get("activityTimeSlotID"));

		try {
			Integer venueId = getVenueIdForActivityTimeSlot(activityTimeSlotID);
			Integer seatId = seatService.findSeatId(venueId, seatName);

			if (seatId != null) {
				seatStatusService.updateSeatStatus(seatId, activityTimeSlotID, 1); // 將狀態更新為1（可用）
				logger.info("Successfully canceled reservation for seat: {}", seatName);
				Map<String, Object> response = new HashMap<>();
				response.put("success", true);
				response.put("message", "座位預留已成功取消");
				return ResponseEntity.ok(response);
			} else {
				logger.warn("No corresponding Seat ID found for Seat Name: {}", seatName);
				Map<String, Object> response = new HashMap<>();
				response.put("success", false);
				response.put("message", "找不到對應的座位");
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			logger.error("Error occurred while canceling seat reservation", e);
			Map<String, Object> response = new HashMap<>();
			response.put("success", false);
			response.put("message", "取消座位預留時發生錯誤");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	private String getVenueAreaNameFromSeatName(String seatName) {
		if (seatName == null || seatName.isEmpty()) {
			return null;
		}
		if (seatName.startsWith("VIP")) {
			return "VIP";
		} else if (seatName.startsWith("A")) {
			return "A";
		} else if (seatName.startsWith("B")) {
			return "B";
		} else {
			logger.warn("Unknown seat area for seat name: {}", seatName);
			return null;
		}
	}

	// 輔助方法：獲取指定區域的價格
	private BigDecimal getPriceForArea(String areaName, Integer activityID) {
		Integer venueId = getVenueIdForActivity(activityID);

		Integer venueAreaId = venueAreaService.findVenueAreaIdByVenueIdAndVenueAreaName(venueId, areaName);
		if (venueAreaId != null) {
			ActivityAreaPrice price = activityAreaPriceService.findActivityAreaPrice(venueAreaId, activityID);
			if (price != null) {
				return price.getActivityAreaPrice().setScale(0, RoundingMode.HALF_UP);
			}
		}
		return BigDecimal.ZERO; // 如果沒有找到價格，返回 0
	}

	private Integer getVenueIdForActivity(Integer activityID) {
		Activity activity = activityService.getOneActivity(activityID);
		return activity.getVenue().getVenueID();
	}

	private Integer getVenueIdForActivityTimeSlot(Integer activityTimeSlotID) {
		ActivityTimeSlot activityTimeSlot = activityTimeSlotService.getActivityTimeSlotById(activityTimeSlotID);
		return activityTimeSlot.getActivity().getVenue().getVenueID();
	}

	// 驗證價格的方法
	private boolean validatePrices(Map<String, Integer> prices) {
		return prices.values().stream().allMatch(price -> price >= 500);
	}
}