package com.tia102g5.article.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tia102g5.article.model.Article;
import com.tia102g5.article.model.ArticleService;
import com.tia102g5.articleImg.model.ArticleImg;
import com.tia102g5.generalmember.model.GeneralMemberService;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	ArticleService articleSvc;
	

	@Autowired
	GeneralMemberService generalMemberSvc;


	/*
	 * This method will serve as addEmp.html handler.
	 */
	@GetMapping("addArticle")
	public String addArticle(ModelMap model) {
		Article article = new Article();
		model.addAttribute("article", article);
		return "back-end/emp/addEmp";
	}

	/*
	 * This method will be called on addEmp.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("insert")
	public String insert(@Valid Article article, BindingResult result, ModelMap model,
			@RequestParam(value = "articlePic", required = false) MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中articlePic欄位的FieldError紀錄 --> 見第172行
		 result = removeFieldError(article, result, "articlePic");

		    if (result.hasErrors()) {
		        return "back-end/emp/addEmp";
		    }
		/*************************** 2.開始新增資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		articleSvc.addArticle(article);
		
		if (parts != null && parts.length > 0) {
	        for (MultipartFile pic : parts) {
	            if (pic != null && !pic.isEmpty()) {
	                try {
	                    ArticleImg articleImg = new ArticleImg();
	                    articleImg.setArticle(article);  // 使用原始的 article 對象
	                    articleImg.setArticlePic(pic.getBytes());
	                    ArticleImgSvc.addArticleImg(articleImg);
	                } catch (IOException e) {
	                    // 記錄錯誤並繼續處理其他圖片
	                    model.addAttribute("warningMessage", "部分圖片上傳失敗：" + pic.getOriginalFilename());
	                }
	            }
	        }
	    }
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<Article> list = articleSvc.getAll();
		model.addAttribute("articleListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/emp/listAllEmp"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("articleID") String articleID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		Article article = articleSvc.getOneArticle(Integer.valueOf(articleID));

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("article", article);
		return "back-end/emp/update_emp_input"; // 查詢完成後轉交update_emp_input.html
	}

	/*
	 * This method will be called on update_emp_input.html form submission, handling POST request It also validates the user input
	 */
	@PostMapping("update")
	public String update(@Valid Article article, BindingResult result, ModelMap model,
			@RequestParam("articlePic") MultipartFile[] parts) throws IOException {

		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		// 去除BindingResult中upFiles欄位的FieldError紀錄 --> 見第172行
		result = removeFieldError(article, result, "articlePic");


		if (result.hasErrors()) {
			return "back-end/emp/update_emp_input";
		}
		/*************************** 2.開始修改資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		articleSvc.updateArticle(article);

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		article = articleSvc.getOneArticle(Integer.valueOf(article.getArticleID()));
		model.addAttribute("article", article);
		return "back-end/emp/listOneEmp"; // 修改成功後轉交listOneEmp.html
	}

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("articleID") String articleID, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始刪除資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		articleSvc.deleteArticle(Integer.valueOf(articleID));
		/*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
		List<Article> list = articleSvc.getAll();
		model.addAttribute("articleListData", list);
		model.addAttribute("success", "- (刪除成功)");
		return "back-end/emp/listAllEmp"; // 刪除完成後轉交listAllEmp.html
	}

	/*
	 * 第一種作法 Method used to populate the List Data in view. 如 : 
	 * <form:select path="articleID" id="articleID" items="${generalMemberListData}" itemValue="memberID" itemLabel="memberName" />
	 */
//	@ModelAttribute("generalMemberListData")
//	protected List<GeneralMember> referenceListData() {
//		// GeneralMemberService generalMemberSvc = new GeneralMemberService();
//		List<GeneralMember> list = generalMemberSvc.getAll();
//		return list;
//	}

	/*
	 * 【 第二種作法 】 Method used to populate the Map Data in view. 如 : 
	 * <form:select path="memberID" id="memberID" items="${generalemberMapData}" />
	 */
	@ModelAttribute("articleCategoryMapData") //
	protected Map<Integer, String> referenceMapData() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		map.put(1, "公告");
		map.put(2, "情報");
		map.put(3, "影音");
		map.put(4, "閒聊");
		return map;
	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	public BindingResult removeFieldError(Article article, BindingResult result, String removedFieldname) {
		List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(Collectors.toList());
		result = new BeanPropertyBindingResult(article, "article");
		for (FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 */
	@PostMapping("listArticles_ByCompositeQuery")
	public String listAllArticle(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<Article> list = articleSvc.getAll(map);
		model.addAttribute("articleListData", list); // for listAllEmp.html 第85行用
		return "back-end/emp/listAllEmp";
	}

}