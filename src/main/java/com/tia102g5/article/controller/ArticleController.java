package com.tia102g5.article.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tia102g5.article.model.Article;
import com.tia102g5.article.model.ArticleService;
import com.tia102g5.articleImg.model.ArticleImg;
import com.tia102g5.articleImg.model.ArticleImgService;
import com.tia102g5.board.model.Board;
import com.tia102g5.board.model.BoardService;
import com.tia102g5.generalmember.model.GeneralMember;
import com.tia102g5.generalmember.model.GeneralMemberService;

@Controller
@RequestMapping("/article")
@Validated
public class ArticleController {

	@Autowired
	ArticleService articleSvc;
	
	@Autowired
	ArticleImgService articleImgSvc;
	
	@Autowired
	GeneralMemberService generalMemberSvc;

	
	@Autowired
	BoardService boardSvc;


	/*
	 * This method will serve as addEmp.html handler.
	 */
	

	
	@GetMapping("addArticle")
	public String addArticle(ModelMap model) {
		Article article = new Article();
		model.addAttribute("article", article);
		
		List<Board> boardList = boardSvc.getAll();
	    model.addAttribute("boardListData", boardList);
	    
	    List<String> categories = articleSvc.getAllCategories();
	    model.addAttribute("articleCategories", categories);
		
	    List<GeneralMember> generalMemberList = generalMemberSvc.getAll();
	    model.addAttribute("generalMemberListData", generalMemberList);
	    
	    
		return "back-end/forum/addArticle";
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
		        return "back-end/forum/addArticle";
		    }
		    
		 // 檢查圖片數量
		    final int MAX_IMAGES = 5; // 最多允許5張圖片
		    if (parts != null && parts.length > MAX_IMAGES) {
		        result.rejectValue("articlePic", "error.articlePic", "最多只能上傳 " + MAX_IMAGES + " 張圖片");
		        return "back-end/forum/addArticle";
		    }

		    // 檢查圖片大小和格式
		    final long MAX_FILE_SIZE = 8 * 1024 * 1024; // 最大8MB
		    if (parts != null) {
		        for (MultipartFile pic : parts) {
		            if (!pic.isEmpty()) {
		                if (pic.getSize() > MAX_FILE_SIZE) {
		                    result.rejectValue("articlePic", "error.articlePic", "圖片大小不能超過 8MB");
		                    return "back-end/forum/addArticle";
		                }

		            }
		        }
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
	                    articleImgSvc.addArticleImg(articleImg);
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
		return "redirect:/article/listAllArticle"; // 新增成功後重導至IndexController_inSpringBoot.java的第58行@GetMapping("/emp/listAllEmp")
	}
	

	/*
	 * This method will be called on listAllEmp.html form submission, handling POST request
	 */
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(
			@RequestParam("articleID") String articleIDStr,
//			@RequestParam(value = "deleteImageID", required = false) Integer deleteImageID,
			ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		
		Integer articleID = Integer.valueOf(articleIDStr);
		
		// 如果有刪除圖片請求
//	    if (deleteImageID != null) {
//	        articleImgSvc.deleteArticleImg(deleteImageID); // 删除指定 ID 的图片
//	    }
		
		/*************************** 2.開始查詢資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		Article article = articleSvc.getOneArticle(articleID);
		
		List<Board> boardList = boardSvc.getAll();
	    model.addAttribute("boardListData", boardList);
	    
	    List<String> categories = articleSvc.getAllCategories();
	    model.addAttribute("articleCategories", categories);
	    
	    List<GeneralMember> generalMemberList = generalMemberSvc.getAll();
	    model.addAttribute("generalMemberListData", generalMemberList);
	    
	   // 查詢對應文章的所有圖片
	    List<ArticleImg> articleImgs = articleImgSvc.getArticleImgsByArticleID(articleID);
	    model.addAttribute("articleImgs", articleImgs);

		

		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("article", article);
		return "back-end/forum/update_article_input"; // 查詢完成後轉交update_emp_input.html
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
			return "back-end/forum/update_emp_input";
		}
		
		 // 檢查圖片數量
	    final int MAX_IMAGES = 5; // 最多允許5張圖片
	    if (parts != null && parts.length > MAX_IMAGES) {
	        result.rejectValue("articlePic", "error.articlePic", "最多只能上傳 " + MAX_IMAGES + " 張圖片");
	        return "back-end/forum/addArticle";
	    }

	    // 檢查圖片大小
	    final long MAX_FILE_SIZE = 8 * 1024 * 1024; // 最大8MB
	    if (parts != null) {
	        for (MultipartFile pic : parts) {
	            if (!pic.isEmpty()) {
	                if (pic.getSize() > MAX_FILE_SIZE) {
	                    result.rejectValue("articlePic", "error.articlePic", "圖片大小不能超過 8MB");
	                    return "back-end/forum/addArticle";
	                }
	            }
	        }
	    }
		
		/*************************** 2.開始修改資料 *****************************************/
		// ArticleService articleSvc = new ArticleService();
		articleSvc.updateArticle(article);
		
		// 處理圖片
	    if (parts != null && parts.length > 0 && !parts[0].isEmpty()) {
	        // 如果有新圖片上傳,先刪除舊圖片
	        articleImgSvc.deleteArticleImg(article.getArticleID());

	        for (MultipartFile pic : parts) {
	            if (pic != null && !pic.isEmpty()) {
	                try {
	                    ArticleImg articleImg = new ArticleImg();
	                    articleImg.setArticle(article);
	                    articleImg.setArticlePic(pic.getBytes());
	                    articleImgSvc.addArticleImg(articleImg);
	                } catch (IOException e) {
	                    // 記錄錯誤並繼續處理其他圖片
	                    model.addAttribute("warningMessage", "部分圖片上傳失敗：" + pic.getOriginalFilename());
	                }
	            }
	        }
	    }
	    // 如果沒有新圖片上傳,保留原有圖片,不做任何處理
		
		

		/*************************** 3.修改完成,準備轉交(Send the Success view) **************/
		model.addAttribute("success", "- (修改成功)");
		article = articleSvc.getOneArticle(Integer.valueOf(article.getArticleID()));
		model.addAttribute("article", article);
		return "back-end/forum/listOneArticle"; // 修改成功後轉交listOneArticle.html
	}

	/*
	 * This method will be called on listAllArticle.html form submission, handling POST request
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
		return "back-end/forum/listAllArticle"; // 刪除完成後轉交listAllEmp.html
	}
	
	
	/*
	 * This method will be called on select_page.html form submission, handling POST request
	 */
	@PostMapping("listArticles_ByCompositeQuery")
	public String listAllArticle(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<Article> list = articleSvc.getAll(map);
		model.addAttribute("articleListData", list); // for listAllEmp.html 第85行用
		return "back-end/forum/listAllArticle";
	}
	
	
	/*
	 * This method will be called on select_page.html form submission, handling POST
	 * request It also validates the user input
	 */
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		@NotEmpty(message="文章編號: 請勿空白")
		@Digits(integer = 4, fraction = 0, message = "文章編號: 請填數字-請勿超過{integer}位數")
		@Min(value = 1, message = "文章編號: 不能小於{value}")
		@Max(value = 100, message = "文章編號: 不能超過{value}")
		@RequestParam("articleID") String articleID,
		ModelMap model) {
		
		
		/***************************2.開始查詢資料*********************************************/
//		ArticleService articleSvc = new ArticleService();
		Article article = articleSvc.getOneArticle(Integer.valueOf(articleID));
		
		List<Article> list = articleSvc.getAll();
		model.addAttribute("articleListData", list);     // for select_page.html 第97 109行用
		model.addAttribute("board", new Board());  // for select_page.html 第133行用
		List<Board> list2 = boardSvc.getAll();
    	model.addAttribute("boardListData",list2);    // for select_page.html 第135行用
		
		if (article == null) {
			model.addAttribute("errorMessage", "查無資料");
			return "back-end/forum/select_page";
		}
		
		/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
		model.addAttribute("article", article);
		model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
		
//		return "back-end/forum/listOneArticle";  // 查詢完成後轉交listOneArticle.html
		return "back-end/forum/select_page"; // 查詢完成後轉交select_page.html由其第158行insert listOneEmp.html內的th:fragment="listOneEmp-div
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
	


	
	@ExceptionHandler(value = { ConstraintViolationException.class })
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleError(HttpServletRequest req,ConstraintViolationException e,Model model) {
	    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	    StringBuilder strBuilder = new StringBuilder();
	    for (ConstraintViolation<?> violation : violations ) {
	          strBuilder.append(violation.getMessage() + "<br>");
	    }
	    //==== 以下第267~271行是當前面第252行返回 /src/main/resources/templates/back-end/forum/select_page.html用的 ====   
//	    model.addAttribute("article", new Article());
//    	ArticleService ArticleSvc = new ArticleService();
	    // 加載所有文章
		List<Article> list = articleSvc.getAll();
		model.addAttribute("articleListData", list);     // for select_page.html 第97 109行用
		model.addAttribute("board", new Board());  // for select_page.html 第133行用
		
		// 加載所有版塊
		List<Board> list2 = boardSvc.getAll();
    	model.addAttribute("boardListData",list2);    // for select_page.html 第135行用
		
    	
    	String message = strBuilder.toString();
	    return new ModelAndView("back-end/forum/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
	}
	

}