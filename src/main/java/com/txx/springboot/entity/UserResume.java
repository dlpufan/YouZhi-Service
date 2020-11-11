package com.txx.springboot.entity;

/**
 * @author 淘米水浇花
 */
public class UserResume {
    private int id;
    private String userPro;
    private String certificate;
    private String jobHistory;
    private int userSalDesire;
    private String userDesire;

    public UserResume() {

    }

    public UserResume(int id, String userPro, String certificate, String jobHistory, int userSalDesire, String userDesire) {
        this.id = id;
        this.userPro = userPro;
        this.certificate = certificate;
        this.jobHistory = jobHistory;
        this.userSalDesire = userSalDesire;
        this.userDesire = userDesire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserPro() {
        return userPro;
    }

    public void setUserPro(String userPro) {
        this.userPro = userPro;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getJobHistory() {
        return jobHistory;
    }

    public void setJobHistory(String jobHistory) {
        this.jobHistory = jobHistory;
    }

    public int getUserSalDesire() {
        return userSalDesire;
    }

    public void setUserSalDesire(int userSalDesire) {
        this.userSalDesire = userSalDesire;
    }

    public String getUserDesire() {
        return userDesire;
    }

    public void setUserDesire(String userDesire) {
        this.userDesire = userDesire;
    }

    @Override
    public String toString() {
        return "UserResume{" +
                "id=" + id +
                ", userPro='" + userPro + '\'' +
                ", certificate='" + certificate + '\'' +
                ", jobHistory='" + jobHistory + '\'' +
                ", userSalDesire=" + userSalDesire +
                ", userDesire='" + userDesire + '\'' +
                '}';
    }
}
