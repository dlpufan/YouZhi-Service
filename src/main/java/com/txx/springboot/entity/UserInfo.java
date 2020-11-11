package com.txx.springboot.entity;

import java.sql.Date;

/**
 * @author 淘米水浇花
 */
public class UserInfo {
    private Integer id;
    private String userTimeOn;
    private Object headImg;
    private String userName;
    private boolean sex;
    private String userNativePlace;
    private String school;
    private Long userNo;
    private Date userBirth;
    private String userProfile;
    private String userEdu;
    private Long userTel;

    public UserInfo() {

    }

    public UserInfo(Integer id, String userTimeOn, Object headImg, String userName, boolean sex, String userNativePlace, String school, Long userNo, Date userBirth, String userProfile, String userEdu, Long userTel) {
        this.id = id;
        this.userTimeOn = userTimeOn;
        this.headImg = headImg;
        this.userName = userName;
        this.sex = sex;
        this.userNativePlace = userNativePlace;
        this.school = school;
        this.userNo = userNo;
        this.userBirth = userBirth;
        this.userProfile = userProfile;
        this.userEdu = userEdu;
        this.userTel = userTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserTimeOn() {
        return userTimeOn;
    }

    public void setUserTimeOn(String userTimeOn) {
        this.userTimeOn = userTimeOn;
    }

    public Object getHeadImg() {
        return headImg;
    }

    public void setHeadImg(Object headImg) {
        this.headImg = headImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getUserNativePlace() {
        return userNativePlace;
    }

    public void setUserNativePlace(String userNativePlace) {
        this.userNativePlace = userNativePlace;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserEdu() {
        return userEdu;
    }

    public void setUserEdu(String userEdu) {
        this.userEdu = userEdu;
    }

    public Long getUserTel() {
        return userTel;
    }

    public void setUserTel(Long userTel) {
        this.userTel = userTel;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userTimeOn='" + userTimeOn + '\'' +
                ", headImg=" + headImg +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", userNativePlace='" + userNativePlace + '\'' +
                ", school='" + school + '\'' +
                ", userNo=" + userNo +
                ", userBirth=" + userBirth +
                ", userProfile='" + userProfile + '\'' +
                ", userEdu='" + userEdu + '\'' +
                ", userTel=" + userTel +
                '}';
    }
}
