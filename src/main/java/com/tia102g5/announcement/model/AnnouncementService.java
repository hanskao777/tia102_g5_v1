package com.tia102g5.announcement.model;

import com.tia102g5.announcement.model.Announcement;
import com.tia102g5.announcement.model.AnnouncementRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Announcement3;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("announcementService")
public class AnnouncementService {

    @Autowired
    AnnouncementRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    // 創建新公告
    public void addAnnouncement(Announcement announcement) {
        repository.save(announcement);
    }

    // 更新現有公告
    public void updateAnnouncement(Announcement announcement) {
        repository.save(announcement);
    }

    // 刪除公告
    public void deleteAnnouncement(Integer announcementID) {
        if (repository.existsById(announcementID))
            repository.deleteByAnnouncementID(announcementID);
    }

    // 獲取單個公告
    public Announcement getOneAnnouncement(Integer announcementID) {
        Optional<Announcement> optional = repository.findById(announcementID);
        return optional.orElse(null);
    }

    // 獲取所有公告
    public List<Announcement> getAll() {
        return repository.findAll();
    }

    // 分頁查詢
//    public Page<Announcement> getAllPaginated(Pageable pageable) {
//        return repository.findAll(pageable);
//    }

    // 按狀態查詢公告
//    public List<Announcement> getAnnouncementsByStatus(Integer status) {
//        return repository.findByAnnouncementStatus(status);
//    }

    // 按公告對象查詢公告
//    public List<Announcement> getAnnouncementsByTarget(String target) {
//        return repository.findByAnnouncementTarget(target);
//    }

    // 按日期範圍查詢公告
//    public List<Announcement> getAnnouncementsByDateRange(Date startDate, Date endDate) {
//        return repository.findByAnnouncementCreateTimeBetween(startDate, endDate);
//    }

    // 複合查詢
    public List<Announcement> getAll(Map<String, String[]> map) {
        return HibernateUtil_CompositeQuery_Announcement3.getAllC(map, sessionFactory.openSession());
    }
}