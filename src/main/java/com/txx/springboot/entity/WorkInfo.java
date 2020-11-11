package com.txx.springboot.entity;

import java.sql.Date;

/**
 * @author 淘米水浇花
 */
public class WorkInfo {
    private Integer id;
    private String title;
    private Integer salary;
    private Integer workType;
    private String tags;
    private String position;
    private String workDescription;
    private Integer workNeedCount;
    private Date sendTime;
    private Integer userID;
    private String workTime;
    private Integer payment;
    private Long userTel;

    public int getPageViews() {
        return pageViews;
    }

    public void setPageViews(int pageViews) {
        this.pageViews = pageViews;
    }

    private int pageViews;


    public WorkInfo() {

    }

    public WorkInfo(Integer id, String title, Integer salary, Integer workType, String tags, String position, String workDescription, Integer workNeedCount, Date sendTime, Integer userID, String workTime, Integer payment, Long userTel) {
        this.id = id;
        this.title = title;
        this.salary = salary;
        this.workType = workType;
        this.tags = tags;
        this.position = position;
        this.workDescription = workDescription;
        this.workNeedCount = workNeedCount;
        this.sendTime = sendTime;
        this.userID = userID;
        this.workTime = workTime;
        this.payment = payment;
        this.userTel = userTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public Integer getWorkNeedCount() {
        return workNeedCount;
    }

    public void setWorkNeedCount(Integer workNeedCount) {
        this.workNeedCount = workNeedCount;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Long getUserTel() {
        return userTel;
    }

    public void setUserTel(Long userTel) {
        this.userTel = userTel;
    }

    @Override
    public String toString() {
        return "WorkInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                ", workType=" + workType +
                ", tags='" + tags + '\'' +
                ", position='" + position + '\'' +
                ", workDescription='" + workDescription + '\'' +
                ", workNeedCount=" + workNeedCount +
                ", sendTime=" + sendTime +
                ", userID=" + userID +
                ", workTime='" + workTime + '\'' +
                ", payment=" + payment +
                ", userTel=" + userTel +
                '}';
    }
}
