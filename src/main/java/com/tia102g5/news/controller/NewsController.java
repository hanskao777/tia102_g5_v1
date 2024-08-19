package com.tia102g5.news.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.tia102g5.administrator.model.Administrator;
import com.tia102g5.announcement.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.tia102g5.news.model.News;
import com.tia102g5.news.model.NewsService;
import com.tia102g5.partnermember.model.PartnerMember;
import com.tia102g5.administrator.model.AdministratorService;

@Controller
@RequestMapping("/news")
@Validated
public class NewsController {

    @Autowired
    NewsService newsSvc;

    @Autowired
    AdministratorService administratorSvc;

//    @GetMapping("/select_page")
//    public String select_page(Model model) {
//        return "front-end/news/select_page";
//    }

    // 管理員消息頁面 沒有側邊攔
    @GetMapping("/listAllNews")
    public String listAllNews(/*HttpSession session,*/ Model model, @RequestParam(defaultValue = "1") int page) {
    	
//    	if(session.getAttribute("adminID") == null) {
//    		return "redirect:/adminLogin";
//    	}
//    	
//    	Integer adminID = (Integer)session.getAttribute("adminID");
//    	Administrator admin = AdministratorService.getOneAdministrator(adminID);
    	
        int pageSize = 10; // 每頁顯示的公告數量
        Page<News> newsPage = newsSvc.getAllPaginated(PageRequest.of(page - 1, pageSize));

        model.addAttribute("news", newsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsPage.getTotalPages());

        return "back-end-admin/announcement-news/news";
    }

    // 首頁消息頁面
    @GetMapping("/allNews")
    public String allNews(Model model, @RequestParam(defaultValue = "1") int page) {
        List<News> newsList = newsSvc.getAll();  // 或者使用分頁版本
        model.addAttribute("newsList", newsList);

        int pageSize = 5; // 每頁顯示的公告數量
        Page<News> newsPage = newsSvc.getAllPaginated(PageRequest.of(page - 1, pageSize));

        model.addAttribute("newsList", newsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", newsPage.getTotalPages());

        return "front-end/announcement-news/news";
    }

    @ModelAttribute("newsListData")
    protected List<News> referenceListData(Model model) {
        List<News> list = newsSvc.getAll();
        return list;
    }

    // 新增消息
    @GetMapping("addNews")
    public String addNews(ModelMap model) {
        News news = new News();
        news.setAdministrator(new Administrator()); // 初始化 Administrator 對象
        model.addAttribute("news", news);
        return "back-end-admin/announcement-news/addNews";
    }

    @PostMapping("insert")
    public String insert(@Valid News news, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors()) {
            return "back-end-admin/announcement-news/addNews";
        }

        newsSvc.addNews(news);

        List<News> list = newsSvc.getAll();
        model.addAttribute("newsListData", list);
        model.addAttribute("success", "- (新增成功)");
        return "redirect:/news/listAllNews";
    }

    // ... （更新、刪除、查詢）"news"
    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("newsID") String newsIDStr, ModelMap model) {
        Integer newsID = Integer.valueOf(newsIDStr);
        News news = newsSvc.getOneNews(newsID);

//        List<Administrator> administratorList = administratorSvc.getAll();
//        model.addAttribute("administratorListData", administratorList);

        model.addAttribute("news", news);
//        return "back-end/news/update_news_input";
        return "back-end-admin/announcement-news/updateNews";

    }

    @PostMapping("update")
    public String update(@Valid News news, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors()) {
//            return "back-end/news/update_news_input";
            return "back-end-admin/announcement-news/updateNews";

        }

        newsSvc.updateNews(news);

        model.addAttribute("success", "- (修改成功)");
        news = newsSvc.getOneNews(news.getNewsID());
        model.addAttribute("news", news);
//        return "back-end/news/listOneNews";
        return "redirect:/news/listAllNews";

    }

    @PostMapping("delete")
    public String delete(@RequestParam("newsID") String newsID, ModelMap model) {
        newsSvc.deleteNews(Integer.valueOf(newsID));
        List<News> list = newsSvc.getAll();
        model.addAttribute("newsListData", list);
        model.addAttribute("success", "- (刪除成功)");
//        return "back-end/news/listAllNews";
        return "front-end/announcement-news/listAllNews";

    }










    @PostMapping("listNewss_ByCompositeQuery")
    public String listAllNews(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap();
        List<News> list = newsSvc.getAll(map);
        model.addAttribute("newsListData", list);
//        return "back-end/news/listAllNews";
        return "front-end/announcement-news/listAllNews";

    }

    @PostMapping("getOne_For_Display")
    public String getOne_For_Display(
            @NotEmpty(message="公告編號: 請勿空白")
            @Digits(integer = 4, fraction = 0, message = "公告編號: 請填數字-請勿超過{integer}位數")
            @Min(value = 1, message = "公告編號: 不能小於{value}")
            @Max(value = 1000, message = "公告編號: 不能超過{value}")
            @RequestParam("newsID") String newsID,
            ModelMap model) {

        News news = newsSvc.getOneNews(Integer.valueOf(newsID));

        if (news == null) {
            model.addAttribute("errorMessage", "查無資料");
//            return "back-end/news/select_page";
            return "front-end/announcement-news/select_page";

        }

        model.addAttribute("news", news);
        model.addAttribute("getOne_For_Display", "true");

//        return "back-end/news/select_page";
        return "front-end/announcement-news/select_page";

    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ModelAndView handleError(HttpServletRequest req, ConstraintViolationException e, Model model) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage() + "<br>");
        }

        List<News> list = newsSvc.getAll();
        model.addAttribute("newsListData", list);

        String message = strBuilder.toString();
        return new ModelAndView("front-end/news/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
    }
}