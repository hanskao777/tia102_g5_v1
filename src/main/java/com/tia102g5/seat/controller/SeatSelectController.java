package com.tia102g5.seat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seatSelect")
public class SeatSelectController {

    @GetMapping
    public String getSeatSelect(ModelMap model) {
        return "/front-end/ticket/seatSelect";
    }

}
