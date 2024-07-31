package com.tia102g5.news.model;

import java.sql.Timestamp;

public class News implements java.io.Serializable {
    private Integer newsID; //消息ID
    private Integer administratorID; //管理員ID
    private String newsTitle; //標題
    private String newsContent; //內容
    private Integer newsStatus; //狀態 0:隱藏;1:正常
    private Timestamp newsCreateTime; //發布時間

    public News() {
        super();
    }

    public News(Integer newsID, Integer administratorID, String newsTitle, String newsContent, Integer newsStatus, Timestamp newsCreateTime) {
        this.newsID = newsID;
        this.administratorID = administratorID;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsStatus = newsStatus;
        this.newsCreateTime = newsCreateTime;
    }

    public Integer getNewsID() {
        return newsID;
    }
    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public Integer getAdministratorID() {
        return administratorID;
    }
    public void setAdministratorID(Integer administratorID) {
        this.administratorID = administratorID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Integer getNewsStatus() {
        return newsStatus;
    }
    public void setNewsStatus(Integer newsStatus) {
        this.newsStatus = newsStatus;
    }

    public Timestamp getNewsCreateTime() {
        return newsCreateTime;
    }
    public void setNewsCreateTime(Timestamp newsCreateTime) {
        this.newsCreateTime = newsCreateTime;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsID=" + newsID +
                ", administratorID=" + administratorID +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", newsStatus=" + newsStatus +
                ", newsCreateTime=" + newsCreateTime +
                '}';
    }
}