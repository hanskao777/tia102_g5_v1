package com.tia102g5.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g5.administrator.model.Administrator;
import com.tia102g5.administrator.model.AdministratorService;
import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.generalmember.model.GeneralMemberService;
import com.tia102g5.partnermember.model.PartnerMember;
import com.tia102g5.partnermember.model.PartnerMemberService;

@Controller
@RequestMapping(value = { "/api","/generalmember", "/partnermember" })
public class LoginController {

	@Autowired
	GeneralMemberService gmemberSvc;

	@Autowired
	PartnerMemberService partnerSvc;
	

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/partnerLogin")
	public String getpartnerLogin() {
		return "partnerLogin";
	}
	


	// 會員登入
	@PostMapping("login")
	public String login(@RequestParam("memberAccount") String memberAccountStr,
			@RequestParam("memberPassword") String memberPasswordStr, HttpSession session, Model model) {

		// 驗證帳號格式
		if (isInvalidEmail(memberAccountStr)) {
			model.addAttribute("errorMessage", "請輸入有效的帳號(email)");
			return "login";
		}

		// 根據帳號獲取會員資料
		GeneralMember generalMember = gmemberSvc.getByMemberAccount(memberAccountStr);

		// 檢查會員是否存在
		if (generalMember == null) {
			model.addAttribute("errorMessage", "會員不存在");
			return "login"; // 直接返回登入頁
		}

		// 檢查密碼
		if (isPasswordCorrect(memberPasswordStr, generalMember.getMemberPassword())) {
			session.setAttribute("memberID", generalMember.getMemberID());
			session.setAttribute("memberName", generalMember.getMemberName());
			session.setAttribute("memberAccount", generalMember.getMemberAccount());
			return "success"; // 登入成功
		} else {
			model.addAttribute("errorMessage", "密碼錯誤，登入失敗!!!");
			return "login"; // 返回登入頁
		}
	}
	
	// 會員中心
	@GetMapping("/memberCenter")
	public String memberCenter(HttpSession session, Model model) {
	    // 檢查用戶是否已登入
	    if (session.getAttribute("memberID") == null) {
	        return "redirect:/generalmember/login";
	    }

	    // 從 session 獲取用戶資訊
	    Integer memberID = (Integer) session.getAttribute("memberID");
	    
	    // 從數據庫獲取最新的用戶資訊
	    GeneralMember member = gmemberSvc.getOneGeneralMember(memberID);
	    
	    // 將用戶資訊添加到模型中
	    model.addAttribute("member", member);
	    
	    return "memberCenter"; // 返回會員中心的視圖名稱
	}
	
	
	// 登出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 使會話失效
		return "login"; // 重定向到登入頁
	}

	// 驗證電子郵件格式的輔助方法
	private boolean isInvalidEmail(String email) {
		return email == null || email.trim().isEmpty()
				|| !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}

	// 檢查密碼是否正確的輔助方法
	private boolean isPasswordCorrect(String inputPassword, String storedPassword) {
		return inputPassword.equals(storedPassword);
	}

	// 廠商登入
	@PostMapping("partnerLogin")
	public String partnerLogin(@RequestParam("taxID") String taxIDStr,
			@RequestParam("partnerPassword") String partnerPasswordStr, HttpSession session, Model model) {

		// 驗證帳號格式
		if (taxIDStr == null || taxIDStr.trim().isEmpty() || !taxIDStr.matches("^\\d{8}$")) {
			model.addAttribute("errorMessage", "請輸入有效的帳號(統一編號8位數字)");
			return "partnerLogin";
		}
		// 根據帳號獲取會員資料
		PartnerMember partnerMember = partnerSvc.getByTaxID(taxIDStr);

		// 檢查會員是否存在
		if (partnerMember == null) {
			model.addAttribute("errorMessage", "會員不存在");
			return "partnerLogin"; // 直接返回登入頁
		}

		// 檢查密碼
		if (partnerPasswordStr.equals(partnerMember.getPartnerPassword())) {
			model.addAttribute("taxID", taxIDStr);
			session.setAttribute("partnerName", partnerMember.getPartnerName());
			return "/back-end-partner/partner"; // 登入成功
		} else {
			model.addAttribute("errorMessage", "密碼錯誤，登入失敗!!!");
			return "partnerLogin"; // 返回登入頁
		}

	}
	
}