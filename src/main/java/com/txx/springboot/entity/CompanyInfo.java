package com.txx.springboot.entity;

/**
 * @author 淘米水浇花
 */
public class CompanyInfo {
    private Integer id;
    private Integer userID;
    private String companyDescription;
    private String tags;
    private String name;

    public CompanyInfo() {

    }

    public CompanyInfo(Integer id, Integer userID, String companyDescription, String tags, String name) {
        this.id = id;
        this.userID = userID;
        this.companyDescription = companyDescription;
        this.tags = tags;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userId) {
        this.userID = userId;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "id=" + id +
                ", userId=" + userID +
                ", companyDescription='" + companyDescription + '\'' +
                ", tags='" + tags + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

