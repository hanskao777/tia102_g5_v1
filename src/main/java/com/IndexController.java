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
    
    //Prosecutes的Mapping先移到這邊
    //因為@RestController會讓@RequestMapping("/prosecutes")失效
    //要用@RestController, 傳給前端的才會是Json格式, 所以Prosecutes那邊不能改成用@Controller
    @GetMapping("/adminProsecute")
    public String getAdminProsecute(Model model) {
        model.addAttribute("message", "Welcome to Admin Prosecute Page");
        return "/back-end-admin/admin_prosecute";
    }

    @GetMapping("/adminSidebar")
    public String getAdminSidebar() {
        return "/back-end-admin/admin_sidebar";
    }

}