package com.tia102g5.generalmember.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tia102g5.email.MailService;
import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.generalmember.model.GeneralMemberService;
import com.tia102g5.membercoupon.model.MemberCouponService;
import com.tia102g5.verify.Verify;


import javassist.expr.NewArray;

@Controller
@RequestMapping("/generalmember")
public class GeneralMemberController {

	Timestamp now = Timestamp.valueOf(LocalDateTime.now());

	@Autowired
	GeneralMemberService gmemberSvc;

	@Autowired
	MailService mailService;


	/*
	 * This method will serve as addEmp.html handler.
	 */
	@GetMapping("addGeneralMember")
	public String addGeneralMember(ModelMap model) {
		GeneralMember generalMember = new GeneralMember();
		model.addAttribute("generalMember", generalMember);
		return "back-end/generalmember/addGeneralMember";
	}

	/*
	 * This method will be called on addEmp.html form submission, handling POST
	 * request It also validates the user input
	 */

	@PostMapping("insert")
	public String insert(@Valid GeneralMember generalMember, BindingResult result, ModelMap model,
			@RequestParam("memberPicture") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(generalMember, result, "memberPicture");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
			model.addAttribute("errorMessage", "大頭貼: 請上傳照片");
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] buf = multipartFile.getBytes();
				generalMember.setMemberPicture(buf);
			}
		}
		if (result.hasErrors() || parts[0].isEmpty()) {
			return "back-end/generalmember/addGeneralMember";
		}
		/*************************** 2.開始新增資料 *****************************************/
		// EmpService empSvc = new EmpService();
		gmemberSvc.addGeneralMember(generalMember);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<GeneralMember> list = gmemberSvc.getAll();
		model.addAttribute("generalMemberListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/generalmember/listAllGeneralMember"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
	}

	@GetMapping("register")
	public String register(ModelMap model) {
		GeneralMember generalMember = new GeneralMember();
		model.addAttribute("generalMember", generalMember);
		return "register";
	}

	// 會員註冊
		@PostMapping("register")
		public String register(@Valid GeneralMember generalMember, BindingResult result, ModelMap model,
				@RequestParam("memberPicture") MultipartFile[] parts,HttpSession session) throws IOException {
	
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
			result = removeFieldError(generalMember, result, "memberPicture");
	
			if (parts[0].isEmpty()) { // 使用者未選擇要上傳的圖片時
				model.addAttribute("errorMessage", "大頭貼: 請上傳照片");
			} else {
				for (MultipartFile multipartFile : parts) {
					byte[] buf = multipartFile.getBytes();
					generalMember.setMemberPicture(buf);
				}
			}
			if (result.hasErrors() || parts[0].isEmpty()) {
				return "register";
			}
	
			
			/*************************** 2.開始新增資料 *****************************************/
	
			try {
				gmemberSvc.addGeneralMember(generalMember); // 假設您有一個服務方法來保存資料
	
				String verificationCode = String.valueOf((int) (Math.random() * 900000) + 100000);
				
				System.out.println("驗證碼: " + verificationCode);
		        
		        // 設置有效期為30分鐘
		        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(30);
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        String formattedExpirationTime = expirationTime.format(formatter);
		        
		        System.out.println("驗證碼有效期至: " + formattedExpirationTime);
		    
			
				// 發送驗證郵件
				String subject = "您的驗證碼";
				String messageText = "您的驗證碼是：" + verificationCode + "有效時間30分鐘";
				mailService.sendMail("kai199202232578@gmail.com", subject, messageText);
	
				// 將驗證碼存入會話
				session.setAttribute("verificationCode", verificationCode);
				model.addAttribute("successMessage", "您的驗證碼已發送至您的郵箱 ");
				return "verifyPage";
	
			} catch (Exception e) {
				model.addAttribute("errorMessage", "註冊失敗，請稍後再試。");
				return "register"; // 返回註冊表單的視圖名稱
			}
	
		}

	

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST
	 * request
	 */

	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("memberID") String memberID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// EmpService empSvc = new EmpService();
		GeneralMember generalMember = gmemberSvc.getOneGeneralMember(Integer.valueOf(memberID));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("generalMember", generalMember);
		return "back-end/generalmember/update_generalmember_input"; // 查詢完成後轉交update_emp_input.html
	}

	/*
	 * This method will be called on update_emp_input.html form submission, handling
	 * POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid GeneralMember generalMember, BindingResult result, ModelMap model,
			@RequestParam("memberPicture") MultipartFile[] parts, @RequestParam("birthday") String birthdayStr)
			throws IOException {

		Date birthday = null;

		result = removeFieldError(generalMember, result, "birthday");
		{

			if (birthdayStr != null && birthdayStr.trim().length() != 0) {
				try {
					birthday = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr).getTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				model.addAttribute("errorMessage", "生日請勿空白");
			}
		}

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(generalMember, result, "memberPicture");

		if (parts[0].isEmpty()) { // 使用者未選擇要上傳的新圖片時
			// EmpService empSvc = new EmpService();
			byte[] memberPicture = gmemberSvc.getOneGeneralMember(generalMember.getMemberID()).getMemberPicture();
			generalMember.setMemberPicture(memberPicture);
		} else {
			for (MultipartFile multipartFile : parts) {
				byte[] memberPicture = multipartFile.getBytes();
				generalMember.setMemberPicture(memberPicture);
			}
		}
		if (result.hasErrors()) {
			return "back-end/generalmember/update_generalmember_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		// EmpService empSvc = new EmpService();
		gmemberSvc.updateGeneralMember(generalMember);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		generalMember = gmemberSvc.getOneGeneralMember(Integer.valueOf(generalMember.getMemberID()));
		model.addAttribute("generalMember", generalMember);
		return "back-end/generalmember/listOneGeneralMember"; // 修改成功後轉交listOneEmp.html
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST
	 * request
	 */

	@PostMapping("delete")
	public String delete(@RequestParam("memberID") String memberID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// EmpService empSvc = new EmpService();
		gmemberSvc.deleteGeneralMember(Integer.valueOf(memberID));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<GeneralMember> list = gmemberSvc.getAll();
		model.addAttribute("generalMemberListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end/generalmember/listAllGeneralMember"; // 刪除完成後轉交listAllEmp.html
	}

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${deptListData}" itemValue="deptno"
	 * itemLabel="dname" />
	 */
//	@ModelAttribute("deptListData")
//	protected List<DeptVO> referenceListData() {
//		// DeptService deptSvc = new DeptService();
//		List<DeptVO> list = deptSvc.getAll();
//		return list;
//	}

	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : <form:select
	 * path="deptno" id="deptno" items="${depMapData}" />
	 */
//	@ModelAttribute("deptMapData") //
//	protected Map<Integer, String> referenceMapData() {
//		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
//		map.put(10, "財務部");
//		map.put(20, "研發部");
//		map.put(30, "業務部");
//		map.put(40, "生管部");
//		return map;
//	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(GeneralMember generalMember, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname)).collect(Collectors.toList());
		result = new BeanPropertyBindingResult(generalMember, "generalMember");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request
	 */
	
	@PostMapping("listGeneralmember_ByCompositeQuery")
	public String listAllGeneralMember(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<GeneralMember> list = gmemberSvc.getAll(map);
		model.addAttribute("generalMemberListData", list); // for listAllEmp.html 第85行用
		return "back-end/generalmember/listAllGeneralMember";
	}
	
	@GetMapping("/member/{id}")
	public String getMemberById(@PathVariable("id") Integer id, Model model) {
	    GeneralMember member = gmemberSvc.getById(id); // 獲取會員資料
	    model.addAttribute("member", member); // 將會員資料添加到模型
	    return "memberCenter"; // 返回前端模板名稱
	}

}