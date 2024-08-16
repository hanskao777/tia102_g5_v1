package com.tia102g5.activityareaprice.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityAreaPriceService {
	@Autowired
	private ActivityAreaPriceRepository activityAreaPriceRepository;

	// 用場館區域ID跟活動ID來查找活動區域價格ID
	public ActivityAreaPrice findActivityAreaPrice(Integer venueAreaID, Integer activityID) {
		return activityAreaPriceRepository.findActivityAreaPrice(venueAreaID, activityID);
	}
}
