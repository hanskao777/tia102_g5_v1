package com.tia102g5.announcement.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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

import com.tia102g5.announcement.model.Announcement;
import com.tia102g5.announcement.model.AnnouncementService;
import com.tia102g5.administrator.model.Administrator;
import com.tia102g5.administrator.model.AdministratorService;

@Controller
@RequestMapping("/announcement")
@Validated
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementSvc;

    @Autowired
    AdministratorService administratorSvc;

//    @GetMapping("/select_page")
//    public String select_page(Model model) {
////        return "back-end/announcement-news/select_page";
//        return "front-end/announcement-news/select_page";
//
//    }

//    @GetMapping("/listAllAnnouncement")
//    public String listAllAnnouncement(Model model) {
//        List<Announcement> announcements = announcementSvc.getAll();
//        model.addAttribute("announcements", announcements);
//        return "back-end-partner/announcement-news/announcement";
////        return "front-end/announcement-news/listAllAnnouncement";
//
//    }
    // 管理員公告頁面 沒有側邊攔
    @GetMapping("/listAllAnnouncement")
    public String listAllAnnouncement(Model model, @RequestParam(defaultValue = "1") int page) {
        int pageSize = 10; // 每頁顯示的公告數量
        Page<Announcement> announcementPage = announcementSvc.getAllPaginated(PageRequest.of(page - 1, pageSize));

        model.addAttribute("announcements", announcementPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", announcementPage.getTotalPages());

        return "back-end-admin/announcement-news/announcement";
    }

    // 廠商公告頁面
    @GetMapping("/allAnnouncement")
    public String allAnnouncement(Model model, @RequestParam(defaultValue = "1") int page) {
        List<Announcement> announcementList = announcementSvc.getAll();
        model.addAttribute("announcements", announcementList);

        int pageSize = 5; // 每頁顯示的公告數量
        Page<Announcement> announcementPage = announcementSvc.getAllPaginated(PageRequest.of(page - 1, pageSize));

        model.addAttribute("announcements", announcementPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", announcementPage.getTotalPages());

        return "back-end-partner/announcement-news/announcement";
    }
    

    @ModelAttribute("announcementListData")
    protected List<Announcement> referenceListData(Model model) {
        List<Announcement> list = announcementSvc.getAll();
        return list;
    }

    // 新增公告
    @GetMapping("addAnnouncement")
    public String addAnnouncement(ModelMap model) {
//        Announcement announcement = new Announcement();
//        model.addAttribute("announcement", announcement);
        Announcement announcement = new Announcement();
        announcement.setAdministrator(new Administrator()); // 初始化 Administrator 對象
        model.addAttribute("announcement", announcement);
//        List<Administrator> administratorList = administratorSvc.getAll();
//        model.addAttribute("administratorListData", administratorList);

        return "back-end-admin/announcement-news/addAnnouncement";
//        return "front-end/announcement-news/addAnnouncement";

    }

    @PostMapping("insert")
    public String insert(@Valid Announcement announcement, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors()) {
            return "back-end-admin/announcement-news/addAnnouncement";
//            return "front-end/announcement-news/addAnnouncement";

        }

        announcementSvc.addAnnouncement(announcement);

        List<Announcement> list = announcementSvc.getAll();
        model.addAttribute("announcementListData", list);
        model.addAttribute("success", "- (新增成功)");
        return "redirect:/announcement/listAllAnnouncement";
    }


    // 修改公告
    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("announcementID") String announcementIDStr, ModelMap model) {
        Integer announcementID = Integer.valueOf(announcementIDStr);
        Announcement announcement = announcementSvc.getOneAnnouncement(announcementID);

//        List<Administrator> administratorList = administratorSvc.getAll();
//        model.addAttribute("administratorListData", administratorList);

        model.addAttribute("announcement", announcement);
//        return "back-end/announcement/update_announcement_input";
        return "back-end-admin/announcement-news/updateAnnouncement";

    }

    @PostMapping("update")
    public String update(@Valid Announcement announcement, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors()) {
//            return "back-end/announcement/update_announcement_input";
            return "back-end-admin/announcement-news/updateAnnouncement";

        }

        announcementSvc.updateAnnouncement(announcement);

        model.addAttribute("success", "- (修改成功)");
        announcement = announcementSvc.getOneAnnouncement(announcement.getAnnouncementID());
        model.addAttribute("announcement", announcement);
//        return "back-end/announcement/listOneAnnouncement";
//        return "back-end-admin/announcement-news/announcement";
        return "redirect:/announcement/listAllAnnouncement";

    }

    @PostMapping("delete")
    public String delete(@RequestParam("announcementID") String announcementID, ModelMap model) {
        announcementSvc.deleteAnnouncement(Integer.valueOf(announcementID));
        List<Announcement> list = announcementSvc.getAll();
        model.addAttribute("announcementListData", list);
        model.addAttribute("success", "- (刪除成功)");
//        return "back-end/announcement/listAllAnnouncement";
        return "back-end-admin/announcement-news/announcement";

    }








    @PostMapping("listAnnouncements_ByCompositeQuery")
    public String listAllAnnouncement(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap();
        List<Announcement> list = announcementSvc.getAll(map);
        model.addAttribute("announcementListData", list);
//        return "back-end/announcement/listAllAnnouncement";
        return "front-end/announcement-news/listAllAnnouncement";

    }

    @PostMapping("getOne_For_Display")
    public String getOne_For_Display(
            @NotEmpty(message="公告編號: 請勿空白")
            @Digits(integer = 4, fraction = 0, message = "公告編號: 請填數字-請勿超過{integer}位數")
            @Min(value = 1, message = "公告編號: 不能小於{value}")
            @Max(value = 1000, message = "公告編號: 不能超過{value}")
            @RequestParam("announcementID") String announcementID,
            ModelMap model) {

        Announcement announcement = announcementSvc.getOneAnnouncement(Integer.valueOf(announcementID));

        if (announcement == null) {
            model.addAttribute("errorMessage", "查無資料");
//            return "back-end/announcement/select_page";
            return "front-end/announcement-news/select_page";

        }

        model.addAttribute("announcement", announcement);
        model.addAttribute("getOne_For_Display", "true");

//        return "back-end/announcement/select_page";
        return "front-end/announcement-news/select_page";

    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ModelAndView handleError(HttpServletRequest req, ConstraintViolationException e, Model model) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage() + "<br>");
        }

        List<Announcement> list = announcementSvc.getAll();
        model.addAttribute("announcementListData", list);

        String message = strBuilder.toString();
//        return new ModelAndView("back-end/announcement/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);
        return new ModelAndView("front-end/announcement-news/select_page", "errorMessage", "請修正以下錯誤:<br>"+message);

    }
    @GetMapping("/announcement")
    public String getAnnouncement(Model model) {
        List<Announcement> announcements = announcementSvc.getAll();
        model.addAttribute("announcements", announcements);
        return "back-end-partner/announcement-news/announcement";
    }

}