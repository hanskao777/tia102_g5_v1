package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	//導向首頁
    @GetMapping("/")
    public String index(Model model) {
        return "index"; //view
    }
    
    //網站 Header
    @GetMapping("/header")
    public String getHeader() {
        return "header";
    }
    
    //網站 Footer
    @GetMapping("/footer")
    public String getFooter() {
        return "footer";
    }
    
    //導向後台主頁
    @GetMapping("/backEndPartner")
    public String getPartner() {
        return "/back-end-partner/partner";
    }
    
    //後臺主頁側邊欄，用於 partner.js 第二行
    @GetMapping("/partnerSidebar")
    public String getPartnerSidebar() {
        return "/back-end-partner/partner_sidebar";
    }

}