package com.tia102g5.news.model;

import com.tia102g5.announcement.model.Announcement;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_News3;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("newsService")
public class NewsService {

    @Autowired
    NewsRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    // 創建新消息
    public void addNews(News news) {
        repository.save(news);
    }

    // 更新現有消息
    public void updateNews(News news) {
        repository.save(news);
    }

    // 刪除消息
    public void deleteNews(Integer newsID) {
        if (repository.existsById(newsID))
            repository.deleteByNewsID(newsID);
    }

    // 獲取單個消息
    public News getOneNews(Integer newsID) {
        Optional<News> optional = repository.findById(newsID);
        return optional.orElse(null);
    }

    // 獲取所有消息
    public List<News> getAll() {
        return repository.findAll();
    }

    // 分頁查詢
    public Page<News> getAllPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // 按狀態分頁查詢公告
    public Page<News> getNewsByStatus(Integer status, Pageable pageable) {
        return repository.findByNewsStatus(status, pageable);
    }

    // 按日期範圍分頁查詢公告
    public Page<News> getNewsByDateRange(Date startDate, Date endDate, Pageable pageable) {
        return repository.findByNewsCreateTimeBetween(startDate, endDate, pageable);
    }

    // 複合查詢
    public List<News> getAll(Map<String, String[]> map) {
        // 假設您有一個類似的 News 工具類
        // 您可能需要創建這個工具類或調整查詢方法
        return HibernateUtil_CompositeQuery_News3.getAllC(map, sessionFactory.openSession());
    }
}