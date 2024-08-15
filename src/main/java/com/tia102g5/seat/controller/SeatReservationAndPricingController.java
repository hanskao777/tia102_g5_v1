package com.tia102g5.seat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seatReservationAndPricing")
public class SeatReservationAndPricingController {

    @GetMapping
    public String getseatReservationAndPricing(ModelMap model) {
        return "/back-end-partner/ticket/seatReservationAndPricing";
    }

}
