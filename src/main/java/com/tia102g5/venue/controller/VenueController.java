package com.tia102g5.venue.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tia102g5.partnermember.model.PartnerMember;
import com.tia102g5.venue.model.Venue;
import com.tia102g5.venue.model.VenueService;
import com.tia102g5.venuerental.model.VenueRental;
import com.tia102g5.venuerental.model.VenueRentalService;

@Controller
@RequestMapping("/venue")
public class VenueController {
//    @Autowired
//    VenueService venueService;//暫時用不到

    // 前端場館介紹頁面的Mapping
    @GetMapping("/venueIntroduction")
    public String getVenueRentalFrontPage() {
    	return "/front-end/venue/venueIntroduction";
    }
    
}
